package com.example.kotlinflowspractice.stateflowvsshared

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun mainStateVsShared() {
    runBlocking {
        val sharedFlow = MutableSharedFlow<Int>()

        val stateFlow = MutableStateFlow(0)

//    it has value property and sharedFlow does not have this
        //it holds the last known value
//    println(stateFlow.value)

        launch {
            stateFlow.collect {
                println("state Flow $it")
            }
        }

        launch {
            sharedFlow.collect {
                println(it)
            }
        }


        launch {
            // share flow can emit repeated value
            repeat(5) {
                sharedFlow.emit(1)
            }
        }

        launch {
            // state flow can  emit only distinct  value. so the output will be 1 only
            repeat(5) {
                stateFlow.emit(1)
            }
        }


    }
}

fun main(){
    runBlocking {

        val stateFlowUsingSharedFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

        launch {
            stateFlowUsingSharedFlow.distinctUntilChanged().collect{
                println(it)
            }
        }


        launch {
            repeat(5){
                stateFlowUsingSharedFlow.emit(1)
            }
        }


    }
}