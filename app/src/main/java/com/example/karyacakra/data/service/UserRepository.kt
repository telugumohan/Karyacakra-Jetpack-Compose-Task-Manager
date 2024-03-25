package com.example.karyacakra.data.service


import android.content.Intent
import android.content.IntentSender
import com.example.karyacakra.model.sign_in.MySignInResult
import com.example.karyacakra.model.sign_in.MyUserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<MyUserData>

    suspend fun signIn(): IntentSender?
    suspend fun signInWithIntent(intent: Intent): MySignInResult
    suspend fun signOut()
    fun getSignedInUser(): MyUserData?



    //suspend fun authenticate(email: String, password: String)
   // suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount()
   // suspend fun deleteAccount()


    // New
}