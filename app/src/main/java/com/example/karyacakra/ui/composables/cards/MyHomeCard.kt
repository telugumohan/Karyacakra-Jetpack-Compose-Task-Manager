package com.example.karyacakra.ui.composables.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.karyacakra.R
import com.example.karyacakra.ui.composables.RoundCheckBox
import com.example.karyacakra.ui.theme.KaryachakraTheme

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyHomeCard() {
    KaryachakraTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {paddingValues ->
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .widthIn(max = 400.dp)
                        .heightIn(max = 640.dp)
                        .padding(24.dp),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = Color.Green, contentColor = Color.Black),
                                    shape = CircleShape
                                ) {
                                    Text(
                                        text = "8/13",
                                        style = MaterialTheme.typography.labelMedium,
                                        //color = Color.Green,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "Wed 20 Mar 2024",
                                    style = MaterialTheme.typography.labelSmall
                                )
                                var isShowingMenu by remember { mutableStateOf(false) }

                                IconButton(
                                    onClick = {
                                        isShowingMenu = !isShowingMenu
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = null
                                    )
                                    DropdownMenu(expanded = isShowingMenu, onDismissRequest = { isShowingMenu = false }) {
                                        DropdownMenuItem(
                                            text = {
                                                Text(text = "Edit Task")
                                            },
                                            onClick = {
                                                // Go to edit screen &
                                                isShowingMenu = false
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = {
                                                Text(text = "Delete Task")
                                            },
                                            onClick = {
                                                // Go to edit screen &
                                                isShowingMenu = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Complete the KaryaChakra App.",
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            Image(
                                painter = painterResource(id = R.drawable.r9),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(144.dp)
                                    .padding(12.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            Text(
                                text = "The KaryaChakra App is a wheel of tasks, where you can fulfill your daily tasks.",
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Start
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            Text(
                                text = "https://KaryaChakra.app.in",
                                style = MaterialTheme.typography.titleSmall,
                                textAlign = TextAlign.Start
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        item {
                            subs.forEach {
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    // horizontalArrangement = Arrangement.spacedBy(2.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                ) {
                                    var roundCheckBoxState by remember { mutableStateOf(true) }
                                    RoundCheckBox(
                                        modifier = Modifier.width(60.dp),
                                        isChecked = roundCheckBoxState,
                                        onClick = { roundCheckBoxState = !roundCheckBoxState },
                                        enabled = true
                                    )
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Start
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SampleCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Wed 20 Mar 2024",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Complete the KaryaChakra App.",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.r9),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(144.dp)
                        .padding(12.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {
                Text(
                    text = "The KaryaChakra App is a wheel of tasks, where you can fulfill your daily tasks.",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )
            }
            item {
                Text(
                    text = "https://KaryaChakra.app.in",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start
                )
            }
            item {
                subs.forEach {
                    Row(
                        verticalAlignment = Alignment.Top,
                        // horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        var roundCheckBoxState by remember { mutableStateOf(false) }
                        RoundCheckBox(
                            modifier = Modifier.width(60.dp),
                            isChecked = roundCheckBoxState,
                            onClick = { roundCheckBoxState = !roundCheckBoxState },
                            enabled = true
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}



val subs = listOf(
    "my sub-task one, need to complete this before today 23:59",
    "my sub-task two, need to complete this before today 23:59",
    "my sub-task three, need to complete this before today 23:59",
    "my sub-task four, need to complete this before today 23:59",
    "my sub-task five, need to complete this before today 23:59",
    "my sub-task one, need to complete this before today 23:59",
    "my sub-task two, need to complete this before today 23:59",
    "my sub-task three, need to complete this before today 23:59",
    "my sub-task four, need to complete this before today 23:59",
    "my sub-task five, need to complete this before today 23:59",
    "my sub-task one, need to complete this before today 23:59",
    "my sub-task two, need to complete this before today 23:59",
    "my sub-task three, need to complete this before today 23:59",
    "my sub-task four, need to complete this before today 23:59",
    "my sub-task five, need to complete this before today 23:59",
)

val cardData = listOf(
    "Title",
    R.drawable.r9,
    "Description",
    "link",
    listOf("sub-task-1", "sub-task-2", "sub-task-3", "sub-task-4")
)