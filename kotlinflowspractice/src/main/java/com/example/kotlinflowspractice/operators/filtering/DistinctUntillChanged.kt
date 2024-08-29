package com.example.kotlinflowspractice.operators.filtering

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

val sourceFlow = flow {
    emit(1)
    emit(1)
    emit(1)
    emit(2)
    emit(2)
    emit(2)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
}

//it is used to remove duplicate value

fun main() = runBlocking {
    sourceFlow.distinctUntilChanged().collectLatest { println(it) }
}