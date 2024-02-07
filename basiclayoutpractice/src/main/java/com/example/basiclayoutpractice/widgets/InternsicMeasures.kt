package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier.height(IntrinsicSize.Min).background(color = Color.White)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxHeight().width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Preview(backgroundColor = 0xfffff)
@Composable
fun InternesicMeasurePreview() {
//    InterensicMeasure()
    TwoTexts(text1 = "Amir", text2 = "Rashid")
}