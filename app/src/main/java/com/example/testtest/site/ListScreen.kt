package com.example.testtest.site

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
}


@Composable
fun ListScreen(
    navigateToDetailScreen: (String) -> Unit,
    listViewModel: ListViewModel = viewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        TestTextField(navigateToDetailScreen, listViewModel)

    }
}

@Composable
fun TestTextField(
    navigateToDetailScreen: (String) -> Unit,
    listViewModel: ListViewModel
) {
    var myValue by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 20.dp)
        ) {
            OutlinedTextField(
                value = myValue,
                onValueChange = { newValue -> myValue = newValue },
                label = { Text("Write a text here...") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = {
                listViewModel.addItem(myValue)
                myValue = ""
            }, colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(text = "Add", color = Color.Red)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn {
        items(listViewModel.enteredText) { text ->
            Card(
                modifier = Modifier
                    .clickable { navigateToDetailScreen(text) }
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = text,
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { listViewModel.removeItem(text) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = "List",
            fontSize = 32.sp,
            color = Color.Black,
        )
    }
}