package com.example.coroutinepractice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield


fun main12() = runBlocking {
    // Launch a CPU-bound task
    val job = launch(Dispatchers.Default) {
        for (i in 1..5) {
            if (isActive) {  // Ensure the coroutine is still active
                doCpuBoundTask(i)
                yield()  // Allow other coroutines to run
            } else {
                println("Coroutine is no longer active, exiting.")
                return@launch
            }
        }
    }

    // Launch another coroutine to demonstrate cooperative multitasking
    val anotherJob = launch(Dispatchers.Default) {
        repeat(5) { i ->
            println("Another task running: Step $i")
            delay(100)  // Simulate some work
        }
    }

    // Wait for a short time and then cancel the job
    delay(300)
    job.cancelAndJoin()
    println("Main program complete")
}

suspend fun doCpuBoundTask(step: Int) {
    println("Performing CPU-bound task: Step $step")
    // Simulate some CPU work
    for (i in 1..1_000_000) {
        // Dummy calculation to mimic CPU-bound work
        val temp = i * i
    }
}



fun main() = runBlocking {
    // Launch a coroutine
    val job = launch {
        repeat(5) { i ->

            println("Coroutine working: $i")
            delay(3000) // Simulate some work

        }
    }

    delay(1200) // Let the coroutine run for some time

    println("Canceling coroutine...")
    job.cancelAndJoin() // Cancel and wait for the coroutine to finish
    println("Coroutine is cancelled and completed.")
}