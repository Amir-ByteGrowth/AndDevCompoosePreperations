package com.example.statehoistingpreperations.propertydrilling

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ParentComponent(viewModel: MyViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column {
        ChildComponent1(viewModel)
    }
}

@Composable
fun ChildComponent1(viewModel: MyViewModel) {
    Column {
        Text(text = viewModel.text)
        ChildComponent2(viewModel)
    }
}

@Composable
fun ChildComponent2(viewModel: MyViewModel) {
    Column {
        Text(text = viewModel.text)
        ChildComponent3(viewModel)
    }
}

@Composable
fun ChildComponent3(viewModel: MyViewModel) {
    Column {
        Text(text = viewModel.text)
        Button(onClick = { viewModel.updateText("Hello, Compose!") }) {
            Text("Update Text")
        }
    }
}