package com.example.karyacakra.ui.composables.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.karyacakra.common.converters.datePickerStateToString
import com.example.karyacakra.ui.composables.dialogs.MyDatePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerCard (
    dateState: DatePickerState,
    modifier: Modifier = Modifier
) {
    var popUpPicker by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.border(width = 1.dp, color = Color.Gray).clickable { popUpPicker = !popUpPicker },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Date", style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = datePickerStateToString(dateState) , style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = { popUpPicker = !popUpPicker }) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
    if (popUpPicker) {
        MyDatePickerDialog(closeDialog = { popUpPicker = false }, state = dateState)
    }
}