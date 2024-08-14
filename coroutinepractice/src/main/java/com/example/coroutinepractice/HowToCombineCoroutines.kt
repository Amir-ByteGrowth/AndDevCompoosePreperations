package com.example.coroutinepractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HowToCombineCoroutines : ViewModel() {


}

fun main5() = runBlocking {
    // Launch the first job
    val job1 = launch {
        delay(1000L)
        println("Job 1 is done")
    }

    // Launch the second job
    val job2 = launch {
        delay(500L)
        println("Job 2 is done")
    }

    // Launch the third job
    val job3 = launch {
        delay(2500L)
        println("Job 3 is done")
    }
//    job1.join()
//    job2.join()
//    job3.join()


    joinAll(job1,job2,job3)


}


//if you want result from coroutine then we will use asynch

fun main() = runBlocking {
    // Launch parallel jobs with async
    val deferred1 = async(Dispatchers.IO) {
        delay(1000L)
        "Result from Job 1"
    }

    val deferred2 = async(Dispatchers.Default) {
        delay(2500L)
        "Result from Job 2"
    }

    val deferred3 = async {
        delay(1500L)
        println("3rd job")
        "Result from Job 3"
    }

    // Await all results
    val result1 = deferred1.await()
    val result2 = deferred2.await()
    val result3 = deferred3.await()

    println("All jobs are done")
    println(result1)
    println(result2)
    println(result3)
}


fun main6() = runBlocking {
    val job1 = launch(Dispatchers.IO) {
        // Runs on a background thread for I/O tasks
        println("Job 1 is running on ${Thread.currentThread().name}")
    }

    val job2 = launch(Dispatchers.Default) {
        // Runs on a background thread for CPU-intensive tasks
        println("Job 2 is running on ${Thread.currentThread().name}")
    }

    val job3 = launch(Dispatchers.IO) {
        // Runs on the main thread (requires Android or a UI environment)
        delay(3000)
        println("Job 3 is running on ${Thread.currentThread().name}")
    }

    // Wait for all jobs to finish
    joinAll(job1, job2, job3)
}