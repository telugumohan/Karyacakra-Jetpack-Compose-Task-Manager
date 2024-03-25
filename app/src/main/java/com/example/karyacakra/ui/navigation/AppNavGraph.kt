package com.example.karyacakra.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.karyacakra.AppViewModelProvider
import com.example.karyacakra.ui.screens.add_task.AddTaskScreen
import com.example.karyacakra.ui.screens.add_task.AddTaskScreenDestination
import com.example.karyacakra.ui.screens.add_task.AddTaskViewModel
import com.example.karyacakra.ui.screens.edit_task.EditTaskScreen
import com.example.karyacakra.ui.screens.edit_task.EditTaskScreenDestination
import com.example.karyacakra.ui.screens.edit_task.EditTaskViewModel
import com.example.karyacakra.ui.screens.get_started.GetStartedScreen
import com.example.karyacakra.ui.screens.get_started.GetStartedScreenDestination
import com.example.karyacakra.ui.screens.home.HomeScreen
import com.example.karyacakra.ui.screens.home.HomeScreenDestination
import com.example.karyacakra.ui.screens.home.HomeViewModel
import com.example.karyacakra.ui.screens.profile.ProfileScreen
import com.example.karyacakra.ui.screens.profile.ProfileScreenDestination
import com.example.karyacakra.ui.screens.profile.ProfileViewModel
import com.example.karyacakra.ui.screens.sign_in.SignInScreen
import com.example.karyacakra.ui.screens.sign_in.SignInScreenDestination
import com.example.karyacakra.ui.screens.sign_in.SignInViewModel
import com.example.karyacakra.ui.screens.sign_up.SignUpScreen
import com.example.karyacakra.ui.screens.sign_up.SignUpScreenDestination
import com.example.karyacakra.ui.screens.splash.SplashScreen
import com.example.karyacakra.ui.screens.splash.SplashScreenDestination
import kotlinx.coroutines.launch

@Composable
fun AppNavGraph(
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    navHostController: NavHostController = rememberNavController(),


    signInViewModel: SignInViewModel = viewModel(factory = AppViewModelProvider.Factory),
    profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    editTaskViewModel: EditTaskViewModel = viewModel(factory = AppViewModelProvider.Factory),
    addTaskViewModel: AddTaskViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    NavHost(
        navController = navHostController,
        startDestination = SplashScreenDestination.route,
    ) {
        composable(route = SplashScreenDestination.route) {
            SplashScreen(
                onAppStart = {
                    signInViewModel.onAppStart(
                        navigateToHome = {
                            navHostController.navigate(route = HomeScreenDestination.route) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            )
        }
        composable(route = GetStartedScreenDestination.route) {
            GetStartedScreen()
        }
        composable(route = HomeScreenDestination.route) {
            HomeScreen(
                onProfileClick = {
                    navHostController.navigate(route = ProfileScreenDestination.route)
                },
                navigateToEdit = {taskId ->
                    val _routes = EditTaskScreenDestination.route.replace("{data}", taskId)
                                 navHostController.navigate(route = _routes)
                },
                homeViewModel = homeViewModel,
                onAddTask = {
                    navHostController.navigate(AddTaskScreenDestination.route)
                }
            )
        }
        composable(route = AddTaskScreenDestination.route) {
            AddTaskScreen(
                addTaskViewModel = addTaskViewModel,
                navigateBack =  {
                    navHostController.popBackStack()
                },
                navigateToHome = {
                    navHostController.navigate(HomeScreenDestination.route) {
                        popUpTo(0) {inclusive = true}
                    }
                }
            )
        }
        composable(route = EditTaskScreenDestination.route) {
            val data= it.arguments!!.getString("data")
            EditTaskScreen(
                editTaskViewModel = editTaskViewModel,
                navigateToHome = {
                    navHostController.navigate(HomeScreenDestination.route) {
                        popUpTo(0) {inclusive = true}
                    }
                },
                navigateBack = {
                              navHostController.popBackStack()
                },
                taskId = data!!
            )
        }
        composable(route = ProfileScreenDestination.route) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                onSignIn = {
                           navHostController.navigate(route = SignInScreenDestination.route) {}
                },
                onDelete = {},
                onSignOut = {
                            profileViewModel.signOut()
                    navHostController.navigate(route = SplashScreenDestination.route) {
                        popUpTo(0)
                    }
                },
                navigateBack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(route = SignUpScreenDestination.route) {
            SignUpScreen()
        }
        composable(route = SignInScreenDestination.route) {
            //SignInScreen()
            val state = profileViewModel.state.collectAsStateWithLifecycle().value
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycleScope.launch {
                            profileViewModel.signInWithIntent(intent = result.data ?: return@launch)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_LONG).show()
                    navHostController.navigate(ProfileScreenDestination.route){
                        launchSingleTop = true
                        popUpTo(0) {inclusive = false}
                    }
                    profileViewModel.resetState()
                }
            }
            SignInScreen(
                state = state,
                onSignInClick = {
                    profileViewModel.signIn(launcher = launcher, lifecycleScope = lifecycleScope)
                }
            )
        }
    }
}