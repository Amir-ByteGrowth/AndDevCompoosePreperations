package com.example.kotlinflowspractice.operators.filtering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

class Debounce {
}

suspend fun getResults(value:Int):Int{
    delay(3000)
    return value
}
fun main()= runBlocking {
//    for (i in 0..10){
//        flowOf(getResults(i)).collectLatest { println(it) }
//    }

    flow {
        emit(1)
        delay(100)
        emit(2)
        delay(100)
        emit(3)
    }.onEach { "$it amir" }.collect { value ->
        println("Collecting $value")
        delay(150) // Simulate work
        println("Done $value")
    }
}