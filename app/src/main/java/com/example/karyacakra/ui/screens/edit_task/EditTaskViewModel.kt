package com.example.karyacakra.ui.screens.edit_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karyacakra.data.service.StorageServiceRepository
import com.example.karyacakra.model.MyTask
import com.example.karyacakra.ui.screens.add_task.AddTaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class EditTaskViewModel(
    private val storageServiceRepository: StorageServiceRepository
): ViewModel() {

    private var _addTaskUiState = MutableStateFlow(AddTaskUiState())
    val addTaskUiState: StateFlow<AddTaskUiState> = _addTaskUiState


    private var _currentTask = MutableStateFlow(MyTask())
    val currentTask: StateFlow<MyTask> = _currentTask


    fun getTaskById(taskId: String) {
        viewModelScope.launch {
           _currentTask.value =  storageServiceRepository.getTaskById(taskId) ?: MyTask()
        }
    }

    private var _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun onTaskDetailsChange(newTask: MyTask) {
        _addTaskUiState.update { currentState ->
            currentState.copy(task = newTask, doAllValid = false)
        }
    }

    fun onSaveTask(
        navigateToHome: () -> Unit
    ) {
        val currentTask = _addTaskUiState.value.task
        if (currentTask.title.isEmpty()){
            _errorMessage.value = "Title can't be empty."
            return
        }
        if (currentTask.dueDate.isEmpty()) {
            _errorMessage.value = "Set a valid due date."
            return
        }
        if (currentTask.dueTime.isEmpty()) {
            _errorMessage.value = "Set a valid time."
            return
        }
        // Save the task..
       viewModelScope.launch {
           storageServiceRepository.updateTask(task = currentTask)
           navigateToHome()
       }
    }
}