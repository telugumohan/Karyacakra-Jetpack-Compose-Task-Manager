package com.example.karyacakra.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karyacakra.data.service.StorageServiceRepository
import com.example.karyacakra.model.MyTask
import com.example.karyacakra.ui.composables.cards.sampleTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val storageServiceRepository: StorageServiceRepository
): ViewModel() {
    val tasks = storageServiceRepository.tasks

    fun onCheckedChange(newTask: MyTask) {
        viewModelScope.launch {
            storageServiceRepository.updateTask(task = newTask.copy(completed = !newTask.completed))
        }
    }
    fun onToggleFlag(newTask: MyTask) {
        viewModelScope.launch {
            storageServiceRepository.updateTask(task = newTask.copy(flag = !newTask.flag))
        }
    }
    fun onDeleteTask(newTask: MyTask) {
        viewModelScope.launch {
            storageServiceRepository.deleteTask(taskId = newTask.taskId)
        }
    }


}

data class HomeTasks(
    val tasks: List<MyTask> = sampleTasks
)