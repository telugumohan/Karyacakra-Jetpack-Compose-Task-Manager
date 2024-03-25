package com.example.karyacakra.ui.composables.bottom_sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleModalBottomSheet(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(modifier) {
        Button(onClick = { showBottomSheet = !showBottomSheet }) {
            Text("Toggle Bottom Sheet")
        }

        Text(
            text = "Bottom Sheet State: ${if (showBottomSheet) "Visible" else "Hidden"}",
            modifier = Modifier.padding(top = 16.dp)
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                Column {
                    Text("This is the bottom sheet content.")
                    Button(onClick = { /* Handle button click in bottom sheet */ }) {
                        Text("Button in Bottom Sheet")
                    }
                }
            }
        }
    }
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SimpleBottomSheet(modifier: Modifier = Modifier) {
//    val bottomSheetState = rememberBottomSheetScaffoldState(
//        initialValue = BottomSheetValue.Collapsed
//    ) // Initial state is collapsed
//
//    Column(modifier) {
//        Button(onClick = { bottomSheetState.expand() }) {
//            Text("Show Bottom Sheet")
//        }
//
//        Text(
//            text = "Bottom Sheet State: ${bottomSheetState.currentValue}",
//            modifier = Modifier.padding(top = 16.dp)
//        )
//
//        BottomSheetScaffold(
//            sheetContent = {
//                Column(modifier = Modifier.fillMaxWidth()) {
//                    Text("This is the bottom sheet content.")
//                    Button(onClick = { /* Handle button click in bottom sheet */ }) {
//                        Text("Button in Bottom Sheet")
//                    }
//                }
//            },
//            sheetState = bottomSheetState
//        ) {
//            // Your main content here
//        }
//    }
//}
