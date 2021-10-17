package com.udacity.project4.locationreminders.savereminder

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.PointOfInterest
import com.udacity.project4.R
import com.udacity.project4.base.BaseViewModel
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.launch

class SaveReminderViewModel(val app: Application, val dataSource: ReminderDataSource) :
    BaseViewModel(app) {

    val reminderTitle = MutableLiveData<String>()
    val reminderDescription = MutableLiveData<String>()
    private val _reminderSelectedLocationStr = MutableLiveData<String>()
    private val _selectedPOI = MutableLiveData<PointOfInterest>()
    private val _latitude = MutableLiveData<Double>()
    private val _longitude = MutableLiveData<Double>()


    val reminderSelectedLocationStr: LiveData<String>
        get() = _reminderSelectedLocationStr

    val selectedPOI: LiveData<PointOfInterest>
        get() = _selectedPOI

    val latitude: LiveData<Double>
        get() = _latitude

    val longitude: LiveData<Double>
        get() = _longitude


    /**
     * Clear the live data objects to start fresh next time the view model gets called
     */
    fun onClear() {
        reminderTitle.value = null
        reminderDescription.value = null
        _reminderSelectedLocationStr.value = null
        _selectedPOI.value = null
        _latitude.value = null
        _longitude.value = null
    }

    /**
     * Validate the entered data then saves the reminder data to the DataSource
     */
    fun validateAndSaveReminder(reminderData: ReminderDataItem) : Boolean{
        if (validateEnteredData(reminderData)) {
            saveReminder(reminderData)
            return true
        }
        return false
    }

    /**
     * Save the reminder to the data source
     */
    private fun saveReminder(reminderData: ReminderDataItem) {
        showLoading.value = true
        viewModelScope.launch {
            dataSource.saveReminder(
                ReminderDTO(
                    reminderData.title,
                    reminderData.description,
                    reminderData.location,
                    reminderData.latitude,
                    reminderData.longitude,
                    reminderData.id
                )
            )
            showLoading.value = false
            showToast.value = app.getString(R.string.reminder_saved)
            navigationCommand.value = NavigationCommand.Back
        }
    }

    /**
     * Validate the entered data and show error to the user if there's any invalid data
     */
    fun validateEnteredData(reminderData: ReminderDataItem): Boolean {
        if (reminderData.title.isNullOrEmpty()) {
            showSnackBarInt.value = R.string.err_enter_title
            return false
        }

        if (reminderData.location.isNullOrEmpty()) {
            showSnackBarInt.value = R.string.err_select_location
            return false
        }
        return true
    }

    fun setReminderTitle(title: String) {
        reminderTitle.value = title
    }

    fun setReminderDescription(description: String) {
        reminderDescription.value = description
    }

    fun setReminderSelectedLocationStr(reminderSelectedLocationStr: String) {
        _reminderSelectedLocationStr.value = reminderSelectedLocationStr
    }

    fun setSelectedPoi(poi: PointOfInterest) {
        _selectedPOI.value = poi
    }

    fun setLatitude(latitude: Double) {
        _latitude.value = latitude
    }

    fun setLongitude(longitude: Double) {
        _longitude.value = longitude
    }
}