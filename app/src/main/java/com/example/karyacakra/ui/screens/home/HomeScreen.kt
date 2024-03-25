package com.example.karyacakra.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.karyacakra.R
import com.example.karyacakra.ui.composables.cards.HomeTaskCard
import com.example.karyacakra.ui.composables.cards.sampleTasks
import com.example.karyacakra.ui.navigation.AppNavigationDestination
import com.example.karyacakra.ui.theme.KaryachakraTheme

object HomeScreenDestination: AppNavigationDestination {
    override val route: String
        get() = "home"

}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    homeViewModel: HomeViewModel,
    navigateToEdit: (String) -> Unit,
    onAddTask: () -> Unit
) {
    val tasks =  homeViewModel.tasks.collectAsStateWithLifecycle(initialValue = emptyList()).value
    KaryachakraTheme(
        darkTheme = false,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Karya Chakra")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    actions = {
                        IconButton(onClick = onProfileClick) {
                            Icon(painter = painterResource(id = R.drawable.baseline_account_circle_24), contentDescription = null)
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onAddTask() }
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        ) {paddingValues ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (tasks.isNotEmpty()) {
                    items(items = tasks, key = {it.taskId}) {currentTask ->
                        HomeTaskCard(
                            task = currentTask,
                            onCheckedChange = {homeViewModel.onCheckedChange(newTask = currentTask)},
                            onEdit = { navigateToEdit(currentTask.taskId) },
                            onToggleFlag = { homeViewModel.onToggleFlag(newTask = currentTask) },
                            onDelete = { homeViewModel.onDeleteTask(newTask = currentTask) }
                        )
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Add a Karya",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
             }
        }
    }
}