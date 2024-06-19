package com.example.statehoistingpreperations

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextStateHolder {
    private val _uiState = MutableStateFlow(UiState())
    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    fun updateText(text:String){
        this._uiState.value=UiState(text)
    }
}