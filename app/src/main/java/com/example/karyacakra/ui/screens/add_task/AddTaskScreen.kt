package com.example.karyacakra.ui.screens.add_task

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karyacakra.common.TaskPriority
import com.example.karyacakra.model.MyTask
import com.example.karyacakra.ui.composables.MyOutlinedTextFielder
import com.example.karyacakra.ui.composables.cards.DatePickerCard
import com.example.karyacakra.ui.composables.cards.SetPriorityCard
import com.example.karyacakra.ui.composables.cards.TimePickerCard
import com.example.karyacakra.ui.composables.cards.ToggleFlagCard
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import com.example.karyacakra.ui.theme.KaryachakraTheme

object AddTaskScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "add_task"

}

//@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navigateBack: () -> Unit = {},
    addTaskViewModel: AddTaskViewModel,
    navigateToHome: () -> Unit
) {
    KaryachakraTheme {
        val addTaskUiState by addTaskViewModel.addTaskUiState.collectAsState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Add Karya")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigateBack()
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) {paddingValues ->
            AddEditBody(
                addTaskUiState = addTaskUiState,
                onTaskDetailsChange = addTaskViewModel::onTaskDetailsChange,
                onSaveTask = {
                    addTaskViewModel.onSaveTask(navigateToHome)
                },
                contentPaddingValues = paddingValues
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBody(
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    addTaskUiState: AddTaskUiState,
    onTaskDetailsChange: (MyTask) -> Unit,
    onSaveTask: () -> Unit,
) {
    val dueDateState = rememberDatePickerState()
    val dueTimeState = rememberTimePickerState()
    LaunchedEffect(dueTimeState, dueDateState) {
        val time = "${dueTimeState.hour}:${dueTimeState.minute}"
        val dateInMillis = dueDateState.selectedDateMillis ?: 0
        val dateInMillisString = "$dateInMillis"
        onTaskDetailsChange(addTaskUiState.task.copy(dueTime = time, dueDate = dateInMillisString))
    }
    LazyColumn(
        contentPadding = contentPaddingValues,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            MyOutlinedTextFielder(
                value = addTaskUiState.task.title,
                onValueChange = {onTaskDetailsChange(addTaskUiState.task.copy(title = it))},
                labelText = "Title"
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            MyOutlinedTextFielder(
                value = addTaskUiState.task.description,
                onValueChange = {onTaskDetailsChange(addTaskUiState.task.copy(description = it))},
                labelText = "Description"
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            MyOutlinedTextFielder(
                value = addTaskUiState.task.myUrl,
                onValueChange = {onTaskDetailsChange(addTaskUiState.task.copy(myUrl = it))},
                labelText = "Url"
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            DatePickerCard(dateState = dueDateState)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            TimePickerCard(timeState = dueTimeState)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            SetPriorityCard(
                currentValue = TaskPriority.getByName(addTaskUiState.task.priority).name,
                onPriorityChange = {
                   onTaskDetailsChange(addTaskUiState.task.copy(priority = it))
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            ToggleFlagCard(
                isFlagged = addTaskUiState.task.flag,
                onToggleFlag = {onTaskDetailsChange(addTaskUiState.task.copy(flag = !addTaskUiState.task.flag))}
            )
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    onSaveTask()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(text = "Save", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}



