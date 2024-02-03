package com.example.testtest.screens

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testtest.help_api.Header


@Composable
fun StartScreen(
    navigateToListScreen: () -> Unit,
    navigateToDrinkScreen: () -> Unit,
    navigateToSnakeScreen: () -> Unit
) {

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
        ButtonLayout(
            navigateToListScreen = navigateToListScreen,
            navigateToDrinkScreen = navigateToDrinkScreen,
            navigateToSnakeScreen = navigateToSnakeScreen
        )
    }
}

@Composable
fun ButtonLayout(
    navigateToListScreen: () -> Unit,
    navigateToDrinkScreen: () -> Unit,
    navigateToSnakeScreen: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp),

        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Create a list", navigateToListScreen)
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(
            text = "Cocktail recipes",
            navigateToDrinkScreen
        )
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Snake", navigateToSnakeScreen)
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(text = "Stuff" , { /*TODO*/ })
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),

        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        StartButtons(text = "Surprise!", { /*TODO*/ })
        Spacer(modifier = Modifier.width(30.dp))
        StartButtons(text = "More stuff", { /*TODO*/ })
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
            .shadow(4.dp, RoundedCornerShape(65.dp))
            .size(160.dp),
        shape = CircleShape,
        //border= BorderStroke(2.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(Color.Black)
    )
    {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta,
                textAlign = TextAlign.Center,
                )
        )
    }
}


/*@Composable
fun Header(text: String) {
    val offset = Offset(5.0f, 10.0f)
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = text,
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
}*/