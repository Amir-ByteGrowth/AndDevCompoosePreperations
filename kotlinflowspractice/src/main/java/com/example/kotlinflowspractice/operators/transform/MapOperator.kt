package com.example.kotlinflowspractice.operators.transform

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val sourceFlow = flow {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
    emit(6)
    emit(7)
    emit(8)
    emit(9)
    emit(10)
}

fun main() = runBlocking {
    sourceFlow.map { it * 2 }.collect {
        println(it)
        delay(1000)
    }

}