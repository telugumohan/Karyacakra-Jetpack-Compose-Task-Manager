package com.example.karyacakra.ui.composables.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.karyacakra.R
import com.example.karyacakra.common.TaskPriority

@Composable
fun SetPriorityCard (
    currentValue: String,
    modifier: Modifier = Modifier,
    onPriorityChange: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .border(width = 1.dp, color = Color.Gray)
            .clickable { showMenu = !showMenu },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Priority", style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = currentValue, style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = {showMenu = !showMenu}) {
                Icon(
                    painter = painterResource(id = if (showMenu) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = !showMenu }) {
                TaskPriority.getAllValues().forEach {
                    DropdownMenuItem(
                        text = { Text(text = it, style = MaterialTheme.typography.titleMedium) },
                        onClick = {
                            onPriorityChange(it)
                            showMenu = !showMenu
                        }
                    )
                }
            }
        }
    }

}
