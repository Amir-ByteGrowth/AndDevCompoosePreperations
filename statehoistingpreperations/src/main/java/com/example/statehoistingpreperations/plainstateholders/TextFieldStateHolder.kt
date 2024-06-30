package com.example.statehoistingpreperations.plainstateholders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

class TextFieldStateHolder {
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTextField(typedQuery: String) {
    var userTypedQuery by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue(text = typedQuery, selection = TextRange(typedQuery.length))
        )
    }

    Column {
        TextField(value = userTypedQuery, onValueChange = { userTypedQuery = it })

    }
}