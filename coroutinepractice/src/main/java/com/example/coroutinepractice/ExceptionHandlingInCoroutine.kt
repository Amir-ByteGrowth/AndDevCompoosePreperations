package com.example.coroutinepractice

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.concurrent.thread

class ExceptionHandlingInCoroutine {
}

fun main() {
    runBlocking {
        getData()
    }
}


suspend fun getData(){
    supervisorScope {
        launch {
            delay(500)
            println("Job1")
        }
        launch {
            delay(1000)
            println("Job2")
        }
        launch {
            delay(1500)
            println("Job3")
            throw  RuntimeException("Run Time Exception")
        }
        launch {
            delay(2000)
            println("Job4")
        }
    }
}