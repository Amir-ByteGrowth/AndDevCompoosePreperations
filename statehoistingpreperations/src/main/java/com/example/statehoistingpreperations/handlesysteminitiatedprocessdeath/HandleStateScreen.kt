package com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.savedstate.SavedStateRegistryOwner

@Composable
fun HandleStateScreen(
    handleSystemInitiatedProcessDeathViewModel: HandleSystemInitiatedProcessDeathViewModel = viewModel(),
) {
    val counter = handleSystemInitiatedProcessDeathViewModel.count.collectAsState().value
    Column {
        Text(text = counter.toString())
        Button(onClick = { handleSystemInitiatedProcessDeathViewModel.increment() }) {
            Text(text = "Increment")
        }
    }

}

@Composable
fun ScreenContent(counter: Int, onIncrementClick: () -> Unit) {

}

// Composable function
@Composable
fun CounterScreen(viewModel: HandleSystemInitiatedProcessDeathViewModel =viewModel()) {
    val count = viewModel.count.collectAsState().value

    Column {
        Text(text = "Count: $count")
        Button(onClick = { viewModel.increment() }) {
            Text(text = "Increment")
        }
    }
}

