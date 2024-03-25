package com.example.karyacakra.ui.screens.add_task

import com.example.karyacakra.model.MyTask

data class AddTaskUiState(
    val task: MyTask = MyTask(),
    val doAllValid: Boolean = false
)
