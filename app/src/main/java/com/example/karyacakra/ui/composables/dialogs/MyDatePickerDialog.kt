package com.example.karyacakra.ui.composables.dialogs

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    closeDialog: () -> Unit,
    state: DatePickerState,
) {

    DatePickerDialog(
        onDismissRequest = { closeDialog() },
        confirmButton = {
            TextButton(onClick = {
                closeDialog()
            }) {
                Text(text = "SET")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                closeDialog()
            }) {
                Text(text = "CANCEL")
            }
        }
    ) {
        DatePicker(state = state)
    }
}
