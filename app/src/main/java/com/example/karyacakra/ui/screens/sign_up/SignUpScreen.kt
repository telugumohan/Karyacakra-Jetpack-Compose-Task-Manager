package com.example.karyacakra.ui.screens.sign_up

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.karyacakra.ui.navigation.AppNavigationDestination

object SignUpScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "sign_up"

}

@Composable
fun SignUpScreen() {
    Text(text = "Sign Up")
}