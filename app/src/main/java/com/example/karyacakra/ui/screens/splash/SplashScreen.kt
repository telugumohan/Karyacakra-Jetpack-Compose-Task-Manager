package com.example.karyacakra.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import kotlinx.coroutines.delay

object SplashScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "splash"

}
private const val SPLASH_TIMEOUT = 3000L

@Composable
fun SplashScreen(
    onAppStart: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Karyacakra splash")
        CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
    }
    LaunchedEffect(true) {
        delay(SPLASH_TIMEOUT)
        onAppStart()
    }
}