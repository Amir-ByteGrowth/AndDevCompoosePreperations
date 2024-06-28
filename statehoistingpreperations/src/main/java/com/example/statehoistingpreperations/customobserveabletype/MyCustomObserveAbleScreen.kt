package com.example.statehoistingpreperations.customobserveabletype

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MyCustomObserveAbleScreen(myObservable: MyCustomObserveAble<String> = MyCustomObserveAble()) {
   val state by myObservable.asState()



    Column {
        Text(text = state?: "No Value")
        Button(onClick = { myObservable.setValue("Amir") }) {
            Text(text = "Update Value To Amir")
        }
    }

}