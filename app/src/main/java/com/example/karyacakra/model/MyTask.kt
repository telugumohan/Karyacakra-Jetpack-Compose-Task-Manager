package com.example.karyacakra.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class MyTask (
    @DocumentId val taskId: String = "",
    val title: String = "",
    val description: String = "",
    val imgUri: String = "",
    val myUrl: String = "",
    val priority: String = "",
    val dueDate: String = "",
    val dueTime: String = "",
    val flag: Boolean = false,
    val completed: Boolean = false,
    val userId: String = "",
    @ServerTimestamp val createdAt: Date = Date()
)
