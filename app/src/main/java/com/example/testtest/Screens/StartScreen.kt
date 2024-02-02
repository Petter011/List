package com.example.testtest.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StartScreen(navigateToListScreen: () -> Unit, navigateToDrinkScreen: () -> Unit) {

    Box(
        //modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)))
        )
        Column(
            modifier = Modifier
                //.fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(text = "Welcome")
            HorizontalDivider(thickness = 2.dp, color = Color.Black)
        }
        ButtonLayout(navigateToListScreen = navigateToListScreen, navigateToDrinkScreen = navigateToDrinkScreen)
    }
}



@Composable
fun ButtonLayout(
    navigateToListScreen: () -> Unit,
    navigateToDrinkScreen: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp),

        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Create a list", navigateToListScreen)
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(text = "Drink recipe", navigateToDrinkScreen)
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Hej", navigateToListScreen)
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(text = "Hi" ,navigateToListScreen)
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 90.dp),

        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Stuff",navigateToListScreen)
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(text = "More stuff",navigateToListScreen)
    }
}

@Composable
fun StartButtons(
    text: String,
    onClick: () -> Unit

){
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier
            .shadow(3.dp, RoundedCornerShape(68.dp))
            .size(160.dp),
        shape = CircleShape,
        border= BorderStroke(5.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(Color.LightGray)
    )
    {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black

                )
        )
    }
}
