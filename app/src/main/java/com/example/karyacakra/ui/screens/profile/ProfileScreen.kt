package com.example.karyacakra.ui.screens.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.karyacakra.R
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import com.example.karyacakra.ui.screens.sign_in.SignInViewModel

object ProfileScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "profile"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
   profileViewModel: ProfileViewModel,
   navigateBack: () -> Unit,
   onSignIn: () -> Unit,
   onDelete: () -> Unit,
   onSignOut: () -> Unit
) {
    val profileUiState by profileViewModel.profileUiState.collectAsState(initial = ProfileUiState(isAnonymousAccount = false))
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (profileUiState.isAnonymousAccount) {
                item {
                    // Sign In
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ProfileScreenCard(
                            title = "Sign In",
                            iconResId = R.drawable.baseline_login_24,
                            onClick = onSignIn
                        )
                        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    }
                }
            } else {
                item {
                    // Sign In
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ProfileScreenCard(
                            title = "Sign Out",
                            iconResId = R.drawable.baseline_logout_24,
                            onClick = onSignOut
                        )
                        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    }
                }
                item {
                    // Sign In
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ProfileScreenCard(
                            title = "Delete account",
                            iconResId = R.drawable.baseline_delete_24,
                            onClick = onDelete
                        )
                        Divider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreenCard(
    title: String,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background, contentColor = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(painter = painterResource(id = iconResId), contentDescription = null)
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}