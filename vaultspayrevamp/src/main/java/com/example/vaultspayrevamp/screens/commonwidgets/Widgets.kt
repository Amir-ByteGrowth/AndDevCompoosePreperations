package com.example.vaultspayrevamp.screens.commonwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit

@Composable
fun CommonTextView(label: String, fontFamily: FontFamily,color:Color=Color.Black, size: TextUnit,modifier: Modifier=Modifier) {
    Text(text = label, fontFamily = fontFamily, fontSize = size, color = color, modifier = modifier)
}

@Composable
fun CommonVectorImage(icon: Int, modifier: Modifier = Modifier, size: DpSize, click: () -> Unit) {
    Image(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = "Close", modifier = modifier
            .size(size)
            .clickable { }
    )
}
