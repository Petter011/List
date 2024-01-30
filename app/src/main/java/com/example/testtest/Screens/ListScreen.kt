package com.example.testtest.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ListViewModel : ViewModel() {
    val enteredText = mutableStateListOf<String>()
    fun addItem(text: String) {
        enteredText.add(text)
    }

    fun removeItem(text: String) {
        enteredText.remove(text)
    }

    fun updateItem(originalText: String, editedText: String) {
        val index = enteredText.indexOf(originalText)
        if (index != -1) {
            enteredText[index] = editedText
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToDetailScreen: (String) -> Unit,
    listViewModel: ListViewModel = viewModel()
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(onDismiss = { showSheet = false }, onTextAdded = { newText ->
            listViewModel.addItem(newText)
            showSheet = false
        })
    }
    val customBlue = Color(0xFFbbe9fa)

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier.background(customBlue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            HorizontalDivider()
            ListView(navigateToDetailScreen, listViewModel)
        }
    }
}


@Composable
fun ListView(
    navigateToDetailScreen: (String) -> Unit,
    listViewModel: ListViewModel
) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(listViewModel.enteredText) { text ->
                Card(
                    modifier = Modifier
                        //.clickable { navigateToDetailScreen(text) }
                        .padding(8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    /*colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),*/
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = text,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = { navigateToDetailScreen(text) }) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Edit",
                            )
                        }
                        IconButton(onClick = { listViewModel.removeItem(text) }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    val offset = Offset(5.0f, 10.0f)
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = "This is a List",
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.W900,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 3f
                )
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit, onTextAdded: (String) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var myText by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.fillMaxHeight()
    ) {
        AddStuff(onTextAdded, myText) { newText ->
            myText = newText
        }
    }
}

@Composable
fun AddStuff(
    onTextAdded: (String) -> Unit,
    myText: String,
    onTextChanged: (String) -> Unit
) {

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            OutlinedTextField(
                value = myText,
                onValueChange = onTextChanged,
                label = { Text(text = "Write here") },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                modifier = Modifier
                    .shadow(4.dp, RoundedCornerShape(12.dp))
                    .height(36.dp),
                onClick = {
                    onTextAdded(myText)
                    onTextChanged("")
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}

