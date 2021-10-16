package com.udacity.project4.locationreminders.util

import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem

val validDataItem = ReminderDataItem(
    title = "Go meet friends",
    description = "Hanging out with Friends",
    location = "The tree house",
    latitude = 2.344234234,
    longitude = 1.34234234
)

val dataItemWithAllNulls = ReminderDataItem(
    title = null,
    description = null,
    location = null,
    latitude = null,
    longitude = null
)

val dataItemWithoutLocation = ReminderDataItem(
    title = "Test",
    description = null,
    location = null,
    latitude = null,
    longitude = null
)

val dataItemWithoutCoordinates = ReminderDataItem(
    title = "test",
    description = "test",
    location = "test",
    latitude = null,
    longitude = null
)

val dataItemWithoutRadius = ReminderDataItem(
    title = "test",
    description = "test",
    location = "test",
    latitude = 2.213123123,
    longitude = 1.023432423
)