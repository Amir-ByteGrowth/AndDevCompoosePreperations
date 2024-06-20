package com.example.statehoistingpreperations.derivedstates

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun EdittextDerivedStateOf() {

    var name by remember { mutableStateOf("") }

    val enabled by remember {
        derivedStateOf { name.isNotEmpty() && name.length > 3 }
    }
    Log.d("ReComp", "Called")

    Column {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter Name") })
        if (enabled) {
            Content()
        }
//        Content()
    }


}

@Composable
fun Content() {

    Button(onClick = { /*TODO*/ }) {
        Text(text = "Click Here")
    }
    Log.d("ReComp", "Child")

}