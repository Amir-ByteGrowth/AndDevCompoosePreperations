package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.myBackground(color: Color) = padding(10.dp)
    .clip(RoundedCornerShape(10.dp))
    .background(color = color)


@Composable
fun CustomModifierWidget(modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier = modifier
                .size(100.dp)
                .myBackground(Color.Green), contentAlignment = Alignment.Center
        ) {
            Text(text = "Amir")
        }

        Box(
            modifier = modifier
                .size(150.dp)
                .background(Color.Green)
        ) {

        }
    }
}