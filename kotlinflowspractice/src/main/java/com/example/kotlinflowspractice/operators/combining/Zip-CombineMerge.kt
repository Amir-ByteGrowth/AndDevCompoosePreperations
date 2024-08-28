package com.example.kotlinflowspractice.operators.combining

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking


val numberSourceFlow = flow {
    emit(1)
    delay(200)
    emit(2)
    delay(200)
    emit(3)
}

val letterSourceFlow = flow {
    emit("A")
    delay(300)
    emit("B")
    delay(300)
    emit("C")
//    emit("D")
//    emit("E")
}

fun main() = runBlocking {
    // zip will combine and wait until both flow completed for each emit
//    zip is used for parallel execution
//    if number of emits are not equal it will run only to minimum number of emits than cancel
    letterSourceFlow.zip(numberSourceFlow) { number, letter ->
        "$number $letter"
    }.collectLatest { println(it) }

    // it is used for concurrent execution, it will not wait emit if it is taking time it will use
    // last emitted value until new value, and it will execute all emits no matters which flow has more emits

//    numberSourceFlow.combine(letterSourceFlow){ number,letter ->"$number $letter"}.collectLatest { println(it) }
}