package com.example.navigationgraphpracticew.cleannavgrapgh.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MessageScreen(navigate: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Message Screen")
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navigate.invoke() }, modifier = Modifier.width(100.dp)) {
            Text(text = "Back")
        }
    }
}