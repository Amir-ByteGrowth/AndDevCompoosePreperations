package com.example.kotlinflowspractice.operators.state

import android.util.Log
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking


private val sourceFlow = flow {
    for (i in 0..5) {
        emit(i)
        delay(1000L)
    }
}

fun main() = runBlocking{
    val scope = CoroutineScope(Dispatchers.IO)
    val state = sourceFlow.stateIn(scope)
    val sharedFlow = sourceFlow.shareIn(scope, SharingStarted.Lazily,2)
//   state.collectLatest {
//       println(it)
//   }
    sharedFlow.collectLatest {
        println(it)
    }

//    println(state.value)
}