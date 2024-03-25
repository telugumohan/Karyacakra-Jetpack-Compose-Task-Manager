package com.example.karyacakra.data

import android.content.Context
import com.example.karyacakra.data.service.StorageServiceRepository
import com.example.karyacakra.data.service.UserRepository
import com.example.karyacakra.data.service.impl.StorageServiceImpl
import com.example.karyacakra.data.service.impl.UserRepositoryImpl
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

interface AppContainer {
    val userRepository: UserRepository
    val auth: FirebaseAuth
    val db: FirebaseFirestore
    val storageServiceRepository: StorageServiceRepository
}

class DefaultAppContainer(private val context: Context, private val oneTapClient: SignInClient): AppContainer {

    override val auth: FirebaseAuth
        get() = FirebaseAuth.getInstance()
    override val db: FirebaseFirestore
        get() = Firebase.firestore

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            context = context,
            oneTapClient = oneTapClient,
            auth = auth
        )
    }
    override val storageServiceRepository: StorageServiceRepository by lazy {
        StorageServiceImpl(
            auth = userRepository,
            firestore = db
        )
    }

}