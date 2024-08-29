package com.example.coroutinepractice

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//Use runBlocking to start a coroutine and block the current thread until it completes, usually from a non-coroutine context.,runBlocking is a function that starts a new coroutine and blocks the current thread until it completes. It's used to bridge regular blocking code with coroutines.
fun main() {
    runBlocking {
        val job1 = launch { println("Job1") }
        val job2 = launch { println("job2") }
        val job3 = launch {
            delay(1000)
            println("job3")
        }
//        if we simply use .join with single launch it will wait until the completion of this coroutine then move forward to join other coroutines
//        launch {
//            delay(1000)
//            println("job3")
//        }.join()



        val job4 = launch { println("job4") }
        // when we use join all here it will wait for the execution all four jobs  its output will will 1,2,4,3 if some job is taking time it will be suspended until its completion
        joinAll(job1, job2, job3, job4)

        val job5 = launch { println("Job5") }
        val job6 = launch { println("job6") }
        // if we write join all here it will show job1,2,4,5,6 then 3 because all coroutines are launched before join all
//        joinAll(job1, job2, job3, job4)

    }

}

fun coroutineChecking() {
    // both scope will run cocurrantly jobtag2 only will wait for completion of job2
    runBlocking {
        val scope1 = CoroutineScope(Dispatchers.Default)
        val scope2 = CoroutineScope(Dispatchers.Default)
        scope1.launch {
            val job1 = launch { Log.d("JobTag", "job1") }
            val job2 = launch {
                delay(500)
                Log.d("JobTag", "job2exc")
            }.join()
            launch { Log.d("JobTag2","Job2") }
//            joinAll(job1, job2)
        }

        scope2.launch {
            launch { Log.d("JobTag", "job3") }
            launch { Log.d("JobTag", "job4") }
        }
    }
}