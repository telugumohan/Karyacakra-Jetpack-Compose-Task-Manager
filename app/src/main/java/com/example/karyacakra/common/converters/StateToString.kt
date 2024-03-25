package com.example.karyacakra.common.converters

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
fun datePickerStateToString(dateState: DatePickerState): String {
    val selectedDateMillis = dateState.selectedDateMillis
    val epochDay = selectedDateMillis?.div(MILLIS_IN_DAY) ?: 0L
    val localDate = LocalDate.ofEpochDay(epochDay)

    // Format the LocalDate object
    return localDate.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"))
}


private const val MILLIS_IN_DAY = 24 * 60 * 60 * 1000L


@OptIn(ExperimentalMaterial3Api::class)
fun timePickerStateToString(timeState: TimePickerState): String {
    val hour = timeState.hour
    val minute = timeState.minute
    val localTime = LocalTime.of(hour, minute)

    // Format the LocalTime object
    return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}


@OptIn(ExperimentalMaterial3Api::class)
fun myDatePickerStateToString(selectedDateMillis: Long): String {
    val epochDay = selectedDateMillis.div(MILLIS_IN_DAY)
    val localDate = LocalDate.ofEpochDay(epochDay)

    // Format the LocalDate object
    return localDate.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"))
}





@OptIn(ExperimentalMaterial3Api::class)
fun myTimePickerStateToString(hour: Int, minute: Int): String {

    val localTime = LocalTime.of(hour, minute)

    // Format the LocalTime object
    return localTime.format(DateTimeFormatter.ofPattern("HH:mm"))
}