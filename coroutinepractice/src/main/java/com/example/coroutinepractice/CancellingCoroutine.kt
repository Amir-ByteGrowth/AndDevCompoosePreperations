package com.example.coroutinepractice

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class CancellingCoroutine {
}

fun mainCancelCoroutineWithCustomException() {

    runBlocking {

        try {
            val job1 = launch {
                delay(300)
                println("job1")
            }
            val job2 = launch {
                delay(500)
                println("job2")
            }
            val job3 = launch {
                delay(700)
                println("job3")
            }
            val job4 = launch {
                delay(900)
                println("job4")
            }
            delay(3000)
            job2.cancel(cause = CancellationException(throw RuntimeException("Run time Exception")))
        } catch (e: RuntimeException) {
            println(e.message)
        }

    }

}


// cancelling coroutine
fun mainWithoutCheckingIfisActive(args: Array<String>) = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Hello ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    println("Done!")
}

//fun main(args: Array<String>) = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    val job = launch (Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (i < 5 && isActive) {
//            // print a message twice a second
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("Hello ${i++}")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1000L)
//    println("Cancel!")
//    job.cancel()
//    println("Done!")
//}

fun mainWithEnsureActive(args: Array<String>) = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
            ensureActive()
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Hello ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1000L)
    println("Cancel!")
    job.cancel()
    println("Done!")
}


fun mainCancelBeforeJoin() {
    runBlocking {

        val job1 = launch {
            delay(1000)
            println("Job Completed")
        }

//        if job is cancelled before it is joined coroutine will suspend until job is completed we will not get "job completed "
        job1.cancel()
        job1.join()


    }
}

fun mainCancelWithAwait() {
    runBlocking {

        val job1 = async {
            delay(1000)
            println("Job Completed")
        }

//        if job is cancelled before job.await() it will show exception kotlinx.coroutines.JobCancellationException:
//        DeferredCoroutine was cancelled;
//        Here’s why we get the exception: the role of await is to suspend the coroutine until the result is computed;
//        since the coroutine is cancelled, the result cannot be computed. Therefore, calling await after cancel leads to
        job1.cancel()
        var result = job1.await()


    }
}


//Let other work happen using yield()
//If the work you’re doing is 1) CPU heavy, 2) may exhaust the thread pool and 3) you want to allow the
//thread to do other work without having to add more threads to the pool, then use yield(). The first
//operation done by yield will be checking for completion and exit the coroutine by throwing CancellationException
//if the job is already completed. yield can be the first function called in the periodic check,
// like ensureActive() mentioned above.

suspend fun work1() {
    val startTime = System.currentTimeMillis()
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) {
        yield()
        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("Hello ${i++}")
            nextPrintTime += 500L
        }
    }
}

//suspend fun main() {
//    runBlocking {
//        val job = launch {
//            try {
//                work1()
//            } catch (e: CancellationException) {
//                println(" Work cancelled!")
//            } finally {
//                println("Clean up!")
//            }
//        }
//        delay(1000L)
//        println("Cancel!")
//        job.cancel()
//        println("Done!")
//    }
//}

//A coroutine in the cancelling state is not able to suspend! so if we call suspend function during canceling state it will not work and could not suspend any more.
suspend fun mainCancelWithNoonSuspend() {
    runBlocking {
        val job = launch {
            try {
                delay(3000)
            } catch (e: CancellationException) {
                println(" Work cancelled!")
            } finally {
                println("Clean up!")
            }
        }
        delay(1000L)
        println("Cancel!")
        job.cancel()
        println("Done!")
    }
}


suspend fun work2() {
    val startTime = System.currentTimeMillis()
    var nextPrintTime = startTime
    var i = 0
    while (i < 5) {

        // print a message twice a second
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("Hello ${i++}")
            nextPrintTime += 500L
        }
    }
}


fun main() {
    runBlocking {
        val job = launch {
            try {
                work()
            } catch (e: CancellationException) {
                println("Work cancelled!")
            } finally {
                withContext(NonCancellable) {
                    delay(1000L) // or some other suspend fun
                    println("Cleanup done!")
                }
            }
        }
        delay(1000L)
        println("Cancel!")
        job.cancel()
        println("Done!")
    }
}

