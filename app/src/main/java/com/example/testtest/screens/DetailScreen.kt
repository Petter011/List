package com.example.testtest.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtest.help_api.Header

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun DetailScreen(navController: NavController, text: String, onTextEdited: (String) -> Unit) {

    val (editedText, setEditedText) = remember { mutableStateOf(text) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onTextEdited(editedText) }) {
                Icon(Icons.Rounded.Done, contentDescription = "Done")
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = Brush.verticalGradient(listOf(Color.Magenta, Color.Cyan)))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(text = "Edit")
                HorizontalDivider(thickness = 2.dp, color = Color.Black)
                Spacer(modifier = Modifier.height(60.dp))

                Column(
                    /*modifier = Modifier
                        .fillMaxSize(),*/
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = editedText,
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.height(60.dp))

                    OutlinedTextField(
                        value = editedText,
                        onValueChange = { setEditedText(it) },
                        label = { Text("Edit text here...") },
                        shape = RoundedCornerShape(10.dp),
                    )
                }
            }
        }
    }
}







