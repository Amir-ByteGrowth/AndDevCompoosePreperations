package com.example.navigationgraphreading.sharingviewmodel.byhilt.byhiltscreens.SharedTwo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigationgraphreading.sharingviewmodel.SharedViewmodel


@Composable
fun SharedTwoScreen(
    modifier: Modifier = Modifier,
    sharedViewmodel: SharedViewmodel,
    onButtonClick: () -> Unit,
    navigate: () -> Unit,
) {

    val counter = sharedViewmodel.counter.collectAsState().value

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = counter.toString())

            Button(onClick = {sharedViewmodel.decrementValue()}) {
                Text(text = "Decrement")
            }

            Button(onClick = navigate) {
                Text(text = "Move")
            }
        }
    }
}