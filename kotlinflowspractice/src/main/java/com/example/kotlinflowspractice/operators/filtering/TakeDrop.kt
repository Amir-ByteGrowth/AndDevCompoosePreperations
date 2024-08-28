package com.example.kotlinflowspractice.operators.filtering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking

fun sourceFlow() = flow {
    for (i in 0..10) {
        emit(i)
//        delay(100)
    }
}

fun main() = runBlocking {
//    sourceFlow().filter { it % 2 == 0 }.collectLatest { println(it) }
// first three emits
//    sourceFlow().take(3).collectLatest { println(it) }

    // it will takes the emits until first false from predicate it means whenever the condition will
    // be false for the first time it will stop collection
//    sourceFlow().takeWhile { it<3 }.collectLatest { println(it) }

//    it will drop first 3 items
//    sourceFlow().drop(3).collectLatest { println(it) }

//    it will start emitting after the first false and will emit all values
    sourceFlow().dropWhile { it <8 }.collectLatest { println(it) }
}