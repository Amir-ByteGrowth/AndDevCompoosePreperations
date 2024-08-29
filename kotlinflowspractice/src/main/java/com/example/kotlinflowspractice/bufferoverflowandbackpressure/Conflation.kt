package com.example.kotlinflowspractice.bufferoverflowandbackpressure

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private val sourceFlow = flow {
    repeat(100) {
        delay(50)
        emit(it)
    }
}

fun main() = runBlocking {
    sourceFlow.conflate().collect {
        delay(100)
        println(it)
    }
}