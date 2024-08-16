package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@OptIn(DelicateCoroutinesApi::class)
fun mainWithManualJobStart() {
    runBlocking {
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            println("Job Started")
        }
        // this job will not be executed until we start it manually
        job.start()
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main(){
    runBlocking {
        val job = Job()

        GlobalScope.launch {
            // Do some work
//        delay(2000)
            println("Within a global scope")
            job.complete() // Manually complete the job
        }

        job.invokeOnCompletion {
            println("Job is completed")
        }

    }

}