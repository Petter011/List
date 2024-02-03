package com.example.testtest.help_api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
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
}