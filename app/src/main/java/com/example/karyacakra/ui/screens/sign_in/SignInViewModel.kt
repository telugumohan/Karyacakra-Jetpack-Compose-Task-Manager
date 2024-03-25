package com.example.karyacakra.ui.screens.sign_in

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karyacakra.data.service.UserRepository
import com.example.karyacakra.model.sign_in.MyUserData
import com.example.karyacakra.model.sign_in.SignInState
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SignInViewModel (
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    val myUserData = userRepository.currentUser
    val hasUser = userRepository.hasUser

    var signUpInProgress = mutableStateOf(false)


    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()



    fun signIn(
        launcher: ActivityResultLauncher<IntentSenderRequest>,
        lifecycleScope: LifecycleCoroutineScope
    ) {
        viewModelScope.launch {
            val signInIntentSender = userRepository.signIn()
            // Handle intentSender to start sign-in flow
            lifecycleScope.launch {
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }

        }
    }

    fun getSignedInUser(): MyUserData? {
        return userRepository.getSignedInUser()
    }

    fun signInWithIntent(intent: Intent) {
        viewModelScope.launch {
            val signInResult = userRepository.signInWithIntent(intent)
            _state.value = _state.value.copy(
                isSignInSuccessful = signInResult.data != null,
                signInError = signInResult.errorMessage
            )
        }
    }
    fun resetState() {
        _state.update { SignInState() }
    }

    fun signOut() {
        viewModelScope.launch {
            userRepository.signOut()
        }
    }
    fun onAppStart(navigateToHome: () -> Unit) {
        if (userRepository.hasUser) {
            navigateToHome()
        } else {
            createAnonymousAccount(navigateToHome)
        }
    }
    private fun createAnonymousAccount(navigateToHome: () -> Unit) {
        viewModelScope.launch {
            try {
                userRepository.createAnonymousAccount()
            } catch (e: FirebaseAuthException) {
                _error.value = e.message.toString()
            }
            navigateToHome()
        }
    }
}
