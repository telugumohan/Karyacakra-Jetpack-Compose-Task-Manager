package com.example.karyacakra

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.karyacakra.ui.screens.add_task.AddTaskViewModel
import com.example.karyacakra.ui.screens.edit_task.EditTaskViewModel
import com.example.karyacakra.ui.screens.home.HomeViewModel
import com.example.karyacakra.ui.screens.profile.ProfileViewModel
import com.example.karyacakra.ui.screens.sign_in.SignInViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SignInViewModel(userRepository = myKaryacakraApplication().container.userRepository)
        }
        initializer {
            ProfileViewModel(userRepository = myKaryacakraApplication().container.userRepository)
        }
        initializer {
            HomeViewModel(storageServiceRepository = myKaryacakraApplication().container.storageServiceRepository)
        }
        initializer {
            AddTaskViewModel(storageServiceRepository = myKaryacakraApplication().container.storageServiceRepository)
        }
        initializer {
            EditTaskViewModel(storageServiceRepository = myKaryacakraApplication().container.storageServiceRepository)
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.myKaryacakraApplication(): KaryacakraApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KaryacakraApplication)