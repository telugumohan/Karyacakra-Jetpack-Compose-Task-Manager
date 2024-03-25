package com.example.karyacakra.data.service.impl

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import com.example.karyacakra.R
import com.example.karyacakra.data.service.UserRepository
import com.example.karyacakra.model.sign_in.MySignInResult
import com.example.karyacakra.model.sign_in.MyUserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class UserRepositoryImpl(
    private val context: Context,
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth,
) : UserRepository {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<MyUserData>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let {
                        MyUserData(
                            userId = it.uid,
                            userName = it.displayName,
                            profilePictureUrl = it.photoUrl?.toString(),
                            isAnonymous = it.isAnonymous)
                    } ?: MyUserData(userId = "", userName = null, profilePictureUrl = null))
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }


    override suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception) {
            handleSignInError(e)
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun signInWithIntent(intent: Intent): MySignInResult {
        val credentials = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val x = auth.currentUser!!.linkWithCredential(googleCredentials).await().user
            MySignInResult(
                data = x?.run {
                    MyUserData(
                        userId = uid,
                        userName = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            handleSignInError(e)
            MySignInResult(data = null, errorMessage = e.message)
        }
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            handleSignInError(e)
        }
    }

    override fun getSignedInUser(): MyUserData? = auth.currentUser?.run {
        MyUserData(
            userId = uid,
            userName = displayName,
            profilePictureUrl = photoUrl?.toString(),
            isAnonymous = isAnonymous
        )
    }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun linkAccount() {

    }

    private fun handleSignInError(e: Exception) {
        if (e is CancellationException) return
        Toast.makeText(
            context,
            "Sign-in failed: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
        Log.d("UserRepositoryImpl", "Sign-in failed: ${e.message}")
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(false)
            .build()
    }
}

//val user = auth.signInWithCredential(googleCredentials).await().user
// Check if the user is signing in for the first time
//            if (user?.metadata?.creationTimestamp == user?.metadata?.lastSignInTimestamp) {
//                // User is signing in for the first time, add business details to Firestore
//            }


//            MySignInResult(
//                data = user?.run {
//                    MyUserData(
//                        userId = uid,
//                        userName = displayName,
//                        profilePictureUrl = photoUrl?.toString()
//                    )
//                },
//                errorMessage = null
//            )

class MUserRepositoryImpl(
    private val context: Context,
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth,
) : UserRepository {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<MyUserData>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { MyUserData(userId = it.uid, userName = it.displayName, profilePictureUrl = it.photoUrl?.toString()) } ?: MyUserData(userId = "", userName = null, profilePictureUrl = null))
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    override suspend fun signIn(): IntentSender? {
        // Check if the user is already signed in
        if (auth.currentUser != null) {
            return null
        }

        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception) {
            handleSignInError(e)
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun signInWithIntent(intent: Intent): MySignInResult {
        val credentials = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credentials.googleIdToken

        if (googleIdToken != null) {
            // Signing in with Google Sign-In
            val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
            return try {
                val user = auth.signInWithCredential(googleCredentials).await().user

                // Check if the user is signing in for the first time
                if (user?.metadata?.creationTimestamp == user?.metadata?.lastSignInTimestamp) {
                    // User is signing in for the first time, add business details to Firestore
                }

                MySignInResult(
                    data = user?.run {
                        MyUserData(
                            userId = uid,
                            userName = displayName,
                            profilePictureUrl = photoUrl?.toString()
                        )
                    },
                    errorMessage = null
                )
            } catch (e: Exception) {
                handleSignInError(e)
                MySignInResult(data = null, errorMessage = e.message)
            }
        } else {
            // Signing in anonymously
            return try {
                auth.signInAnonymously().await()
                MySignInResult(
                    data = null,
                    errorMessage = null
                )
            } catch (e: Exception) {
                handleSignInError(e)
                MySignInResult(data = null, errorMessage = e.message)
            }
        }
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            handleSignInError(e)
        }
    }

    override fun getSignedInUser(): MyUserData? = auth.currentUser?.run {
        MyUserData(
            userId = uid,
            userName = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    override suspend fun createAnonymousAccount() {
        TODO("Not yet implemented")
    }

    override suspend fun linkAccount() {
        TODO("Not yet implemented")
    }

    private fun handleSignInError(e: Exception) {
        if (e is CancellationException) return
        Toast.makeText(
            context,
            "Sign-in failed: ${e.message}",
            Toast.LENGTH_LONG
        ).show()
        Log.d("UserRepositoryImpl", "Sign-in failed: ${e.message}")
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(false)
            .build()
    }
}

