package com.example.statehoistingpreperations.increaseperformancebydeffered

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue





@Composable
fun ParentComposable() {
    var count by remember { mutableStateOf(0) }
    Column {
        // Other UI elements
        ChildComposable({count++}) { count }
        ChildComposable2({count++}) { count }
    }
}

@Composable
fun ChildComposable(increment :() ->Unit,countProvider: () -> Int) {
    Column {
        Log.d("Recomp","Child1")
        Text(text = "Count: ${countProvider()}")
        
        Button(onClick = { increment.invoke() }) {
            Text(text = "Increase")
        }
    }
   
}

@Composable
fun ChildComposable2(increment :() ->Unit,countProvider: () -> Int) {
    Log.d("Recomp","Child2")
    Button(onClick = { increment.invoke() }) {
        Text(text = "Increase")
    }
}

