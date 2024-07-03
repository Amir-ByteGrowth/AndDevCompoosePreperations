package com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable

class Conversational {
}

class ConversationViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var message by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("Amir"))
    }
        private set

    fun update(newMessage: TextFieldValue) {
        message = newMessage
    }

    /*...*/
}

val viewModel = ConversationViewModel(SavedStateHandle())

@Composable
fun UserInput(/*...*/) {
    TextField(
        value = viewModel.message,
        onValueChange = { viewModel.update(it) }
    )
}