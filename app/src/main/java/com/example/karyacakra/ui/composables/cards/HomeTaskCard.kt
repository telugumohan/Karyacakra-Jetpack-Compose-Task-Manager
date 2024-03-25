package com.example.karyacakra.ui.composables.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.karyacakra.R
import com.example.karyacakra.common.converters.myDatePickerStateToString
import com.example.karyacakra.model.MyTask

@Composable
fun HomeTaskCard(
    task: MyTask,
    modifier: Modifier = Modifier,
    onCheckedChange: () -> Unit,
    onEdit: () -> Unit,
    onToggleFlag: () -> Unit,
    onDelete: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
       elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onCheckedChange() }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                val dateInMillis = task.dueDate.toLongOrNull() ?: 0L
                Text(
                    text = "${task.dueTime} ${myDatePickerStateToString(dateInMillis)}",
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (task.flag) {
                Icon(painter = painterResource(id = R.drawable.baseline_flag_24), contentDescription = null)
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = !showMenu }) {
                    DropdownMenuItem(text = { Text(text = "Edit task") }, onClick = {
                        onEdit()
                        showMenu = !showMenu
                    })
                    DropdownMenuItem(text = { Text(text = "Toggle flag") }, onClick = {
                        onToggleFlag()
                        showMenu = !showMenu
                    })
                    DropdownMenuItem(text = { Text(text = "Delete task") }, onClick = {
                        onDelete()
                        showMenu = !showMenu
                    })
                }
            }
        }
    }
}

val sampleTasks = listOf(
    MyTask(taskId = "0", completed = true, title = "Complete All.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "1", completed = true, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = false),
    MyTask(taskId = "2", completed = false, title = "Complete Karya Chakra before midnight, Complete Karya Chakra before midnight. Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "3", completed = false, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "4", completed = false, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = false),
    MyTask(taskId = "5", completed = true, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = false),
    MyTask(taskId = "6", completed = true, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "7", completed = false, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "8", completed = true, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = true),
    MyTask(taskId = "9", completed = false, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = false),
    MyTask(taskId = "10", completed = true, title = "Complete Karya Chakra before midnight.", dueTime = "23:59", dueDate = "0", flag = false),
)