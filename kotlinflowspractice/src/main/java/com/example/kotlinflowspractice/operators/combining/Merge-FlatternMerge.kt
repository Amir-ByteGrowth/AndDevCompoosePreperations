package com.example.kotlinflowspractice.operators.combining

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

val sourceFlowA = flow {
    emit("Flow A- Emission 1")
    delay(400)
    emit("Flow A - Emission 2")
}

val sourceFlowB = flow {
    emit("Flow B- Emission 1")
    delay(200)
    emit("Flow B - Emission 2")
}

fun main() = runBlocking {

}