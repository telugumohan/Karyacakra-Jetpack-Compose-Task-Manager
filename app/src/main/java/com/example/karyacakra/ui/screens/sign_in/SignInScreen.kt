package com.example.karyacakra.ui.screens.sign_in


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.karyacakra.R
import com.example.karyacakra.model.sign_in.SignInState
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import com.example.karyacakra.ui.theme.KaryachakraTheme


@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_green_lottie))
    val loginLottieProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    KaryachakraTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                LottieAnimation(
                    modifier = Modifier
                        .size(300.dp),
                    composition = composition,
                    progress = loginLottieProgress,
                )
            }
            Button(
                onClick = onSignInClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue With Google")
            }
        }
    }
}

object SignInScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "sign_in"

}

