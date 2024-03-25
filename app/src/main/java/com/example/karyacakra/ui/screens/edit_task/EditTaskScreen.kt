package com.example.karyacakra.ui.screens.edit_task

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.karyacakra.ui.screens.add_task.AddEditBody
import com.example.karyacakra.ui.theme.KaryachakraTheme

object EditTaskScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "edit_task/{data}"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    editTaskViewModel: EditTaskViewModel,
    navigateBack: () -> Unit = {},
    navigateToHome: () -> Unit,
    taskId: String
) {
    KaryachakraTheme {
        editTaskViewModel.getTaskById(taskId)
        val currentTaskState by editTaskViewModel.currentTask.collectAsState()
        val editTaskUiState by editTaskViewModel.addTaskUiState.collectAsState()
        editTaskViewModel.onTaskDetailsChange(newTask = currentTaskState)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Edit Karya")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigateBack()
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) {paddingValues ->
            AddEditBody(
                addTaskUiState = editTaskUiState,
                onTaskDetailsChange = editTaskViewModel::onTaskDetailsChange,
                onSaveTask = {
                    editTaskViewModel.onSaveTask(navigateToHome)
                },
                contentPaddingValues = paddingValues
            )
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyBottomSheetDemo() {
//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState = rememberStandardBottomSheetState()
//    )
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun Screen() {
//    var bottomSheetVisible by remember { mutableStateOf(false) }
//
//    // Define your bottom sheet content
//    val bottomSheetContent: @Composable ColumnScope.() -> Unit = {
//        Text(
//            text = "Bottom Sheet Content",
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(16.dp)
//        )
//    }
//
//    // Define your button content
//    val buttonContent: @Composable () -> Unit = {
//        Text(
//            text = if (bottomSheetVisible) "Hide Bottom Sheet" else "Show Bottom Sheet",
//            modifier = Modifier.padding(16.dp)
//        )
//    }
//
//    val coroutineScope = rememberCoroutineScope()
//    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
//        bottomSheetState =  rememberStandardBottomSheetState(),//BottomSheetState(BottomSheetValue.Collapsed)
//    )
//
//    // Composable to toggle bottom sheet visibility
//    val toggleBottomSheet: () -> Unit = {
//        bottomSheetVisible = !bottomSheetVisible
//        coroutineScope.launch {
//            if (bottomSheetScaffoldState.bottomSheetState.hasPartiallyExpandedState) {
//                // Animate to hidden state if partially expanded
//                bottomSheetScaffoldState.bottomSheetState.expand()
//            } else if (bottomSheetScaffoldState.bottomSheetState.isVisible) {
//                // Hide the bottom sheet if visible
//               // bottomSheetScaffoldState.bottomSheetState.hide()
//            } else {
//                // Expand the bottom sheet if hidden
//                bottomSheetScaffoldState.bottomSheetState.expand()
//            }
//        }
//    }
//
//    // Custom implementation to manage bottom sheet state
//    val sheetState = remember { mutableStateOf(if (bottomSheetVisible) SheetValue.Expanded else SheetValue.Hidden) }
//
//
//
//    // Composable to display the bottom sheet scaffold
//    BottomSheetScaffold(
//        sheetContent = bottomSheetContent,
//        scaffoldState = bottomSheetScaffoldState,
////        rememberBottomSheetScaffoldState(
////            bottomSheetState =  rememberStandardBottomSheetState()
//////            SheetState(
//////                skipPartiallyExpanded = false,
//////                initialValue = if (bottomSheetVisible) SheetValue.Expanded else SheetValue.Hidden
//////            )
////
////        ),
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            // Display button in the top bar
//            Button(onClick = toggleBottomSheet) {
//                buttonContent()
//            }
//        },
//        content = {
//            // Main content of the screen
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 16.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = "Main Content",
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    )
//}
