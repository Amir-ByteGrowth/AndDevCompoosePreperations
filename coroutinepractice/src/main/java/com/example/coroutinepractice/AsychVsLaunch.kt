package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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


fun mainJobAwait() {
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


suspend fun checkingJoining(coroutineScope: CoroutineScope) {
//if we run without join both coroutine runs concurently and independently if we write .join the will be executed squentialy coproutine is joined with parent scope
    withContext(Dispatchers.Default) {
        delay(100)
        println("First Delay completed")
        coroutineScope.launch {

            delay(1500)
            println("Second Delay")
        }.join()
        println("Third ")
    }
}

fun mainWithExternalScope() {
    runBlocking {
        checkingJoining(CoroutineScope(this.coroutineContext))
    }
}



//In any case, the ViewModel code doesn’t change and with the above, even if the viewModelScope gets
//destroyed, the work using externalScope will keep running. Furthermore, doWork() won’t return until
//veryImportantOperation() completes as with any other suspend call.

class Repository(
    val externalScope: CoroutineScope,
    val ioDispatcher: CoroutineDispatcher,
) {


//    exception handlled in both sides

    suspend fun doWork() {
        val exceptionHandler =
            CoroutineExceptionHandler { coroutineContext, throwable -> println("exception") }
        withContext(ioDispatcher + exceptionHandler) {

            launch {
                try {
                    doSomeOtherWork()
                    throw RuntimeException("Exception")
                } catch (e: RuntimeException) {
                    println(e.message)
                }
            }



            externalScope.launch {
                // if this can throw an exception, wrap inside try/catch
                // or rely on a CoroutineExceptionHandler installed
                // in the externalScope's CoroutineScope
                veryImportantOperation()
            }.join()

            println("Completed")

        }
    }

    suspend fun doWork1() {

        withContext(ioDispatcher) {

            launch {
                    doSomeOtherWork()
            }



            externalScope.launch {
                // if this can throw an exception, wrap inside try/catch
                // or rely on a CoroutineExceptionHandler installed
                // in the externalScope's CoroutineScope
                veryImportantOperation()
            }.join()

            println("Completed")

        }
    }

    suspend fun doSomeOtherWork() {
        delay(1000)
        println("Do Some Work")

    }

    suspend fun veryImportantOperation() {
        delay(1500)
        println("Very Importan Operation")
        throw Exception("exception")
    }


}
