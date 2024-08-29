package com.example.kotlinflowspractice.operators.error_handling

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.runBlocking


fun networkRequest(): Flow<String> = flow {
    emit("Attempting network request")
    delay(1000)
    if (Math.random() < 0.9) {
        throw Exception("Network request failed")
    }
    emit("Network Request succeeded")
}

fun mainTry() = runBlocking {
//    networkRequest().collectLatest { println(it) }
    // retry will only occur if flow fails with exception

//    networkRequest().onEach { it + "OnEach" }.retry(5)
//        .catch { println("${it.message}") }.collectLatest { println(it) }

//    we can also give retry only when specifc condition fulfill
    networkRequest().onEach { it + "OnEach" }.retry{true}
        .catch { println("${it.message}") }.collectLatest { println(it) }
}

//for exception handleing we use retry and retrywhen

fun mainRetryAndRetryWhen() = runBlocking {

    networkRequest().retryWhen { cause, attempt ->
        println(cause.message+"   "+"atempt  $attempt")
        attempt < 3 }.catch { println("${it.message}") }
        .collectLatest {
            println()
        }
    val count=5
    // retry internal uses retrywhen with default value retries= Long.Max and predicate {true}
    networkRequest().retry().collect{}
}

fun mainRetryWhen() = runBlocking {
    var currentDelay = 200L
    var factor = 2
    networkRequest().flowOn(Dispatchers.IO).retryWhen() { cause, attempt ->
        if (cause is Exception) {
            delay(currentDelay)
        }
        currentDelay = (currentDelay * factor)
        if (attempt < 7) {
            return@retryWhen true
        } else {
            return@retryWhen false
        }
    }.catch { println(it.message) }.collect {
        println(it)
    }
}

fun main() = runBlocking {
    networkRequest().retry(retries = 19) { return@retry it is Exception }
        .catch { println("Exception") }.collectLatest {
        println(it)
    }
}