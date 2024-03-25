package com.example.karyacakra.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyOutlinedTextFielder(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = labelText)},
        colors = OutlinedTextFieldDefaults.colors(contentColorFor(backgroundColor = MaterialTheme.colorScheme.background)),
        modifier = modifier
            .fillMaxWidth(),
        maxLines = 5,
    )
}