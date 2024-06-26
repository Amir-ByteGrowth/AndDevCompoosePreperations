package com.example.compoosepreperations.ui.screens.sideeffectsScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SideEffectsScreen(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableStateOf(0)
    }

    SideEffect {
        Log.d("SideEffectsScreenComp",counter.toString())
    }

    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Count: $counter")
            Button(onClick = { counter++ }) {
                Text("Increment")
            }
        }
    }
}

//@Composable
//fun AnalyticsExample(analyticsService: AnalyticsService) {
//    var buttonClicked by remember { mutableStateOf(false) }
//
//    // Send an analytics event whenever buttonClicked changes
//    SideEffect {
//        if (buttonClicked) {
//            analyticsService.trackEvent("ButtonClicked")
//        }
//    }
//
//    Column {
//        Button(onClick = { buttonClicked = true }) {
//            Text("Click me")
//        }
//    }
//}

//@Composable
//fun ViewModelExample(viewModel: ExampleViewModel) {
//    var count by remember { mutableStateOf(0) }
//
//    // Update the ViewModel whenever count changes
//    SideEffect {
//        viewModel.updateCount(count)
//    }
//
//    Column {
//        Text(text = "Count: $count")
//        Button(onClick = { count++ }) {
//            Text("Increment")
//        }
//    }
//}