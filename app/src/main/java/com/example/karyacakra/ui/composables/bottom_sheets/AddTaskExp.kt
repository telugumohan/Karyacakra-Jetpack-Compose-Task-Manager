package com.example.karyacakra.ui.composables.bottom_sheets

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karyacakra.R
import com.example.karyacakra.ui.composables.MyOutlinedTextFielder
import com.example.karyacakra.ui.composables.cards.SampleCard
import com.example.karyacakra.ui.screens.add_task.AddTaskViewModel
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, showBackground = true)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    addTaskViewModel: AddTaskViewModel = viewModel()
) {
    val addTaskUiState by addTaskViewModel.addTaskUiState.collectAsState()
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(   // Added by me..
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 16.dp),
            ) {
               LazyColumn(
                   verticalArrangement = Arrangement.spacedBy(12.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(12.dp)
                       .height(360.dp)
               ) {

                  item {
                      MyOutlinedTextFielder(
                          value = addTaskUiState.task.title,
                          onValueChange = {addTaskViewModel.onTaskDetailsChange(newTask = addTaskUiState.task.copy(title = it))},
                          labelText = "Title *"
                      )
                  }
                   item {
                       MyOutlinedTextFielder(
                           value = addTaskUiState.task.description,
                           onValueChange = {addTaskViewModel.onTaskDetailsChange(newTask = addTaskUiState.task.copy(description = it))},
                           labelText = "Description"
                       )
                   }
                   item {
                       MyOutlinedTextFielder(
                           value = addTaskUiState.task.myUrl,
                           onValueChange = {addTaskViewModel.onTaskDetailsChange(newTask = addTaskUiState.task.copy(myUrl = it))},
                           labelText = "Url"
                       )
                   }
                   item {
                       MyOutlinedTextFielder(
                           value = addTaskUiState.task.title,
                           onValueChange = {addTaskViewModel.onTaskDetailsChange(newTask = addTaskUiState.task.copy(title = it))},
                           labelText = "Title"
                       )
                   }
               }
            }
        },
        sheetBackgroundColor = Color.Green,
        sheetPeekHeight = 0.dp
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { 
                        Text(
                            text = "Karya Chakra"
                        )
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    coroutineScope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        ) {paddingValues ->
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                SampleCard()
            }
        }
    }

//    Button(onClick = {
//        coroutineScope.launch {
//            if (sheetState.isCollapsed) {
//                sheetState.expand()
//            } else {
//                sheetState.collapse()
//            }
//        }
//    }) {
//        Text(text = "Toggle Bottom Sheet")
//    }
}

//data class BottomSheetItem(val title: String, val icon: ImageVector)
////
////@Preview(showBackground = true, showSystemUi = true)
////@OptIn(ExperimentalMaterial3Api::class)
////@ExperimentalFoundationApi
////@Composable
////fun BottomSheetDemo() {
////    //Lets create list to show in bottom sheet
////    val bottomSheetItems = listOf(
////        BottomSheetItem(title = "Notification", icon = Icons.Default.Notifications),
////        BottomSheetItem(title = "Mail", icon = Icons.Default.MailOutline),
////        BottomSheetItem(title = "Scan", icon = Icons.Default.Search),
////        BottomSheetItem(title = "Edit", icon = Icons.Default.Edit),
////        BottomSheetItem(title = "Favorite", icon = Icons.Default.Favorite),
////        BottomSheetItem(title = "Settings", icon = Icons.Default.Settings)
////    )
////
////    //Lets define bottomSheetScaffoldState which will hold the state of Scaffold
////    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
////        bottomSheetState =  rememberStandardBottomSheetState(),//BottomSheetState(BottomSheetValue.Collapsed)
////    )
////
////    val coroutineScope = rememberCoroutineScope()
////    BottomSheetScaffold(
////        scaffoldState = bottomSheetScaffoldState,
////        sheetShape = RoundedCornerShape(topEnd = 30.dp),
////        sheetContent = {
////            //Ui for bottom sheet
////            Column(
////                content = {
////
////                    Spacer(modifier = Modifier.padding(16.dp))
////                    Text(
////                        text = "Bottom Sheet",
////                        modifier = Modifier
////                            .fillMaxWidth(),
////                        textAlign = TextAlign.Center,
////                        fontWeight = FontWeight.Bold,
////                        fontSize = 21.sp,
////                        color = Color.White
////                    )
////                    LazyVerticalGrid(
////                        //cells = GridCells.Fixed(3)
////                        columns = GridCells.Fixed(3), //https://developer.android.com/jetpack/compose/lists
////                    ) {
////                        items(bottomSheetItems.size, itemContent = {
////                            Column(
////                                horizontalAlignment = Alignment.CenterHorizontally,
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(top = 24.dp)
////                                    .clickable {
////
////
////                                    },
////                            ) {
////                                Spacer(modifier = Modifier.padding(8.dp))
////                                Icon(
////                                    bottomSheetItems[it].icon,
////                                    bottomSheetItems[it].title,
////                                    tint = Color.White
////                                )
////                                Spacer(modifier = Modifier.padding(8.dp))
////                                Text(text = bottomSheetItems[it].title, color = Color.White)
////                            }
////
////                        })
////                    }
////                },
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .height(350.dp)
////
////                    //.background(Color(0xFF6650a4))
////                    .background(
////                        brush = Brush.linearGradient(
////                            colors = listOf(
////                                Color(0xFF8E2DE2),
////                                Color(0xFF4A00E0)
////                            )
////                        ),
////                        // shape = RoundedCornerShape(cornerRadius)
////                    )
////                    .padding(16.dp),
////
////                )
////        },
////        sheetPeekHeight = 0.dp,
////        topBar = {
////            //top app bar ui
////            TopAppBar(
////                title = { Text("Bottom Sheet Demo") },
////                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White, titleContentColor = Color.Blue)
////            )
////        }
////    ) {
////
////
////        //Add button to open bottom sheet
////        Column(modifier = Modifier.fillMaxSize()) {
////            Button(
////                modifier = Modifier.padding(20.dp),
////                onClick = {
////                    coroutineScope.launch {
//////                        if (bottomSheetScaffoldState.bottomSheetState.hasPartiallyExpandedState) {
//////                            // Animate to hidden state if partially expanded
//////                            bottomSheetScaffoldState.bottomSheetState.expand()
//////                        } else if (bottomSheetScaffoldState.bottomSheetState.isVisible) {
//////                            // Hide the bottom sheet if visible
//////                            bottomSheetScaffoldState.bottomSheetState.hide()
//////                        } else {
//////                            // Expand the bottom sheet if hidden
//////                            bottomSheetScaffoldState.bottomSheetState.expand()
//////                        }
////                        if (!bottomSheetScaffoldState.bottomSheetState.isVisible) {
////                            // If bottom sheet is collapsed, expand it
////                            bottomSheetScaffoldState.bottomSheetState.expand()
////                        } else {
////                            // If bottom sheet is expanded, collapse it
////                            bottomSheetScaffoldState.bottomSheetState.hide()
////                        }
////                    }
////                }
////            ) {
////                Text(
////                    text = "Click to toggle Bottom Sheet"
////                )
////            }
////
////
////            Text(text = "${bottomSheetScaffoldState.bottomSheetState.currentValue}")
////        }
////    }
////}