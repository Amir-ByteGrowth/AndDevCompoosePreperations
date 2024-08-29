package com.example.kotlinflowspractice.hotvscoldflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main2() {
    runBlocking {
        val sharedFlow = MutableSharedFlow<Int>()


// Collect values from sharedFlow
        launch {
            sharedFlow.collect { value ->
                println("Collector 1 received: $value")
            }
        }

// Collect values from sharedFlow
        launch {
            sharedFlow.collect { value ->
                println("Collector 2 received: $value")
            }
        }

// Emit values to sharedFlow
        launch {
            repeat(3) { i ->
                sharedFlow.emit(i)
            }
        }
    }
}





