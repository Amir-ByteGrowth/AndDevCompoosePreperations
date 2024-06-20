package com.example.statehoistingpreperations.increaseperformancebydeffered

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// deferred states read using lambda functions
@Composable
fun ParentComposable() {
    var count by remember { mutableStateOf(0) }
    Column {
        // Other UI elements
        ChildComposable({count++}) { count }
        ChildComposable2({count++}) { count }
        Log.d("Recomp","Parent")
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
    ChildComposable3(increment,countProvider)
    Button(onClick = { increment.invoke() }) {
        Text(text = "Increase")
    }
}


@Composable
fun ChildComposable3(increment :() ->Unit,countProvider: () -> Int) {
    Log.d("Recomp","Child3")
    Text(text = "Count: ${countProvider()}")
    Button(onClick = { increment.invoke() }) {
        Text(text = "Increase3")
    }
}

//////////

// without deferred read state

@Composable
fun ParentComposableWODRS() {
    var count by remember { mutableStateOf(0) }
    Column {
        // Other UI elements
        ChildComposableWODRS({count++}, count )
        ChildComposable2WODRS({count++}, count )
    }
}

@Composable
fun ChildComposableWODRS(increment :() ->Unit, countProvider:  Int) {
    Column {
        Log.d("Recomp","Child1")
        Text(text = "Count: ${countProvider}")

        Button(onClick = { increment.invoke() }) {
            Text(text = "Increase1")
        }
    }

}

@Composable
fun ChildComposable2WODRS(increment :() ->Unit,countProvider: Int) {
    Log.d("Recomp","Child2")
    ChildComposable3WODRS(increment,countProvider)
    Button(onClick = { increment.invoke() }) {
        Text(text = "Increase2")
    }
}

@Composable
fun ChildComposable3WODRS(increment :() ->Unit,countProvider: Int) {
    Log.d("Recomp","Child3")
    Text(text = "Count: ${countProvider}")
    Button(onClick = { increment.invoke() }) {
        Text(text = "Increase3")
    }
}

