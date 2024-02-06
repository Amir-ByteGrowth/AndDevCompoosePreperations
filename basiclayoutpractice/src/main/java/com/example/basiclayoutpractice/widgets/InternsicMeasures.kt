package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// IntrinsicSize width or height is used to occupy max or min   height and width needed to draw a children
@Composable
fun InterensicMeasure() {
    Column (modifier = Modifier.width(IntrinsicSize.Max)){
        Text(text = "MAD",Modifier.background(color = Color.Gray).fillMaxWidth())
        Text(text = "Skills",Modifier.background(color = Color.Gray).fillMaxWidth())
        Text(text = "Layouts",Modifier.background(color = Color.Gray).fillMaxWidth())
        Text(text = "And Modifiers",Modifier.background(color = Color.Gray).fillMaxWidth())
    }
}

@Preview(backgroundColor = 0xfffff)
@Composable
fun InternesicMeasurePreview() {
    InterensicMeasure()
}