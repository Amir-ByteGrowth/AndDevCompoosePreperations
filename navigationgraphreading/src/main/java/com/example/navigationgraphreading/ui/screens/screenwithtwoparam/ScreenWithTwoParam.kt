package com.example.navigationgraphreading.ui.screens.screenwithtwoparam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color



@Composable
fun ScreenWithTwoParam(modifier: Modifier = Modifier, paramOne: String, paramTwo: String) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Cyan), contentAlignment = Alignment.Center
    ) {
        Text(text = paramOne + "   " + paramTwo)
    }
}