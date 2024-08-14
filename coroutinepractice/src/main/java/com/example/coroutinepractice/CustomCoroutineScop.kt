package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CustomCoroutineScope : CoroutineScope {

    // Define a job for the scope
    private val job = Job()

    // Override coroutineContext to include your custom job and dispatcher
    override val coroutineContext = Dispatchers.Main + job

    // Function to start a coroutine
    fun startTask() {
        launch {
            // Your coroutine code here
            delay(3000)
            println("Task started in MyCustomScope!")
            // Simulate a task
        }
    }

    // Function to cancel all coroutines in this scope
    fun stop() {
        coroutineContext.cancel()
    }
}


fun main() {
    val customCoroutineScope =CustomCoroutineScope()
    customCoroutineScope.startTask()
}