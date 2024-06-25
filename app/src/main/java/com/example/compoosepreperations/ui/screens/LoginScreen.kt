package com.example.compoosepreperations.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var showError by remember {
        mutableStateOf(true)
    }
    LoginContent(showError = showError, onClick ={showError = !showError})

}

@Composable
fun LoginContent( showError: Boolean, onClick: () -> Unit) {
    Column {
        AnimatedVisibility(visible = showError) {
            ShowErrorScreen()
        }

        ContentView(onClick = onClick)
    }
}

@Composable
fun ShowErrorScreen(modifier: Modifier = Modifier) {
    Log.d("ComposableShown","ShowError")
    Text(text = "Error Text")
}

@Composable
fun ContentView(onClick: () -> Unit) {
    Log.d("ComposableShown","ContentView")
    Button(onClick = onClick) {
        Text("Hide/Show Error")
    }
}

//@Composable
//fun LoginScreen(modifier: Modifier = Modifier) {
//    var showError by remember { mutableStateOf(true) }
//
//    LoginContent(
//        modifier = modifier,
//        showError = showError,
//        onToggleError = { showError = !showError }
//    )
//}

//@Composable
//fun LoginContent(modifier: Modifier = Modifier, showError: Boolean, onToggleError: () -> Unit) {
//    Column(modifier = modifier) {
//        AnimatedVisibility(visibleState = MutableTransitionState(showError)) {
//            ShowErrorScreen()
//        }
//        ContentView(onClick = onToggleError)
//    }
//}
//
//@Composable
//fun ShowErrorScreen(modifier: Modifier = Modifier) {
//    Log.d("ComposableShown", "ShowError")
//    Text(text = "Error Text", modifier = modifier)
//}
//
//@Composable
//fun ContentView(modifier: Modifier = Modifier, onClick: () -> Unit) {
//    Log.d("ComposableShown", "ContentView")
//    Button(onClick = onClick, modifier = modifier) {
//        Text("Hide/Show Error")
//    }
//}