package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.security.cert.Extension
import kotlin.coroutines.coroutineContext

class WithContextExample {
}

suspend fun main11() {

    val handlerException = CoroutineExceptionHandler{
        coroutineContext,throwable -> println(throwable.message)
    }

    withContext(Dispatchers.IO) {
        val job1 = launch {
            println("job1")
        }
        val job2 = launch {
            try {
                delay(1000)
                println("job2")
                throw RuntimeException("Runtime ")
            }catch (e : Exception){
                println(e.message)
            }
        }
        val job3 = launch {
            delay(1200)
            println("job3")
        }
        val job4 = launch {
            delay(1400)
            println("job4")
        }

        joinAll(job1,job2,job3,job4)

    }
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch (Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5 && isActive) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Hello ${i++}")
                nextPrintTime += 500L
            }
        }
//
    //   while (i < 5 ) {
////            Because this method instantaneously throws if the job is not active, we can make this the first thing we do in our while loop:
//            ensureActive()
//            // print a message twice a second
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("Hello ${i++}")
//                nextPrintTime += 500L
//            }
//        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    println("Done!")
}