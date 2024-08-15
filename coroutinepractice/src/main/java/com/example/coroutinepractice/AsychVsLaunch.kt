package com.example.coroutinepractice

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AsychVsLaunch {
}

fun main14() {
    runBlocking {
        val job = launch {
            delay(1000)
            println("job1")
        }
        val job2 = launch {
            delay(2000)
            println("job2")
        }
        val job3 = launch {
            delay(1000)
            println("job3")
        }

//        joinAll(job, job2, job3)
        job2.join()
        println("All Completed")
    }
}



fun main() {
    runBlocking {
        val job = async {
            delay(1000)
            println("job1")
        }
        val job2 = async {
            delay(2000)
            println("job2")
        }
        val job3 = async {
            delay(1000)
            println("job3")
        }

        //job.await work same as job.join it will wait untill all coroutines are completed
//        job2.cancel()
       job2.await()
        println("All Completed")
    }
}