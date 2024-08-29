package com.example.kotlinflowspractice.hotvscoldflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        this is hot flow whenever colector will start collecting from this flow it will have last emitted value ,
        //        if you want to have more than one item to be saved for emittion use replay
        val sharedFlow = MutableSharedFlow<Int>()

        // it will show last 3 emitted values
//        val sharedFlow = MutableSharedFlow<Int>(replay = 2)

//        both collector will emit same value

        launch {
            sharedFlow.collect { value ->
                println("Collector 1 received: $value")
            }
        }

        launch {

            sharedFlow.collect { value ->
                println("Collector 2 received: $value")
            }
        }

        launch {
//            here the collector will not collect any value because emission has stopped
            delay(100)
            sharedFlow.collect { value ->
                println("Collector 3 received: $value")
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





