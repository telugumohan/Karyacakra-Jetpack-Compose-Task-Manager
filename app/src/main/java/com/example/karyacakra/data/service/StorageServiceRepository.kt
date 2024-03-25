package com.example.karyacakra.data.service

import com.example.karyacakra.model.MyTask
import kotlinx.coroutines.flow.Flow

interface StorageServiceRepository {
    val tasks: Flow<List<MyTask>>
    suspend fun getTaskById(taskId: String): MyTask?
    suspend fun addNewTask(task: MyTask)
    suspend fun updateTask(task: MyTask)
    suspend fun deleteTask(taskId: String)
}