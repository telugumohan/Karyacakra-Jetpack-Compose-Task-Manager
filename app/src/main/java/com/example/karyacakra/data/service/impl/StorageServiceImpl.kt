package com.example.karyacakra.data.service.impl

import com.example.karyacakra.data.service.StorageServiceRepository
import com.example.karyacakra.data.service.UserRepository
import com.example.karyacakra.model.MyTask
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await

class StorageServiceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: UserRepository
): StorageServiceRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val tasks: Flow<List<MyTask>>
        get() = auth.currentUser.flatMapLatest {
            firestore
                .collection(TASK_COLLECTION)
                .whereEqualTo(USER_ID_FIELD, it.userId)
                .orderBy(CREATED_AT_FIELD, Query.Direction.DESCENDING)
                .dataObjects()
        }

    override suspend fun getTaskById(taskId: String): MyTask?  =
        firestore.collection(TASK_COLLECTION).document(taskId).get().await().toObject()

    override suspend fun addNewTask(task: MyTask) {
        val updatedTask = task.copy(userId = auth.currentUserId)
        firestore.collection(TASK_COLLECTION).add(updatedTask).await()
    }

    override suspend fun updateTask(task: MyTask) {
        firestore.collection(TASK_COLLECTION).document(task.taskId).set(task).await()
    }

    override suspend fun deleteTask(taskId: String) {
        firestore.collection(TASK_COLLECTION).document(taskId).delete().await()
    }

    companion object {
        const val TASK_COLLECTION = "tasks"
        const val USER_ID_FIELD = "userId"
        const val CREATED_AT_FIELD = "createdAt"
    }
}