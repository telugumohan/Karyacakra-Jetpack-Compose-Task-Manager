package com.example.karyacakra.ui.screens.get_started

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.karyacakra.ui.navigation.AppNavigationDestination

object GetStartedScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "get_started"

}

@Composable
fun GetStartedScreen() {
    Text(text = "get started")
}