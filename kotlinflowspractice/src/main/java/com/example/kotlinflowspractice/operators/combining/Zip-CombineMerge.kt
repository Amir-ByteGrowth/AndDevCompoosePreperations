package com.example.kotlinflowspractice.operators.combining

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking


val numberSourceFlow = flow {
    emit(1)
    delay(200)
    emit(2)
    delay(200)
    emit(3)
    emit(4)
    emit(5)
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
//    When we want to run two tasks in parallel and want the results of both tasks in a single callback when both tasks are completed.
    letterSourceFlow.zip(numberSourceFlow) { number, letter ->
        "$number $letter"
    }.collectLatest { println(it) }

    // it is used for concurrent execution, it will not wait emit if it is taking time it will use
    // last emitted value until new value, and it will execute all emits no matters which flow has more emits

//    numberSourceFlow.combine(letterSourceFlow){ number,letter ->"$number $letter"}.collectLatest { println(it) }
}


////////for parallel network calls

private fun doLongRunningTaskOne(): Flow<String> {
    return flow {
        // your code for doing a long running task
        // Added delay to simulate
        delay(5000)
        emit("One")
    }
}
private fun doLongRunningTaskTwo(): Flow<String> {
    return flow {
        // your code for doing a long running task
        // Added delay to simulate
        delay(5000)
        emit("Two")
    }
}


fun mainNetwrokCall() {
   runBlocking {
        doLongRunningTaskOne()
            .zip(doLongRunningTaskTwo()) { resultOne, resultTwo ->
                return@zip resultOne + resultTwo
            }
            .flowOn(Dispatchers.Default)
            .catch { e ->
                // handle exception
            }
            .collect {
                // result
                println(it)
            }
    }
}