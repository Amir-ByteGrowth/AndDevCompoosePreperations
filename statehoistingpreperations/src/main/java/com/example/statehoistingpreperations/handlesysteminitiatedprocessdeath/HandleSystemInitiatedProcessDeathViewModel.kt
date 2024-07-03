package com.example.statehoistingpreperations.handlesysteminitiatedprocessdeath

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HandleSystemInitiatedProcessDeathViewModel(private val savedStateHandle: SavedStateHandle) :ViewModel() {
    private val _count = MutableStateFlow(savedStateHandle.get<Int>("count") ?: 0)
    val count = _count.asStateFlow()

    fun increment(){
        viewModelScope.launch {
            _count.value = count.value+1
            savedStateHandle["count"] = _count.value
        }
    }
}

abstract class CounterViewModelFactory(
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return HandleSystemInitiatedProcessDeathViewModel(handle) as T
    }
}