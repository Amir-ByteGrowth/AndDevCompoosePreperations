package com.example.navigationgraphreading.sharingviewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewmodel @Inject constructor() : ViewModel() {

    val _counter = MutableStateFlow(0)

    val counter = _counter.asStateFlow()

    fun incrementValue(){
        _counter.value += 1
    }

    fun decrementValue(){
        _counter.value -= 1
    }

}