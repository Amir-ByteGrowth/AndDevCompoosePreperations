package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProblemUsingWithContextInsideWithContext {
}

val handler = CoroutineExceptionHandler { _, exception ->
    println("Caught $exception")
}

fun main() = runBlocking {
    val externalScope = CoroutineScope(Dispatchers.Default+ handler)
    val repository = Repository(externalScope, Dispatchers.IO)

    repository.doWork2()
}

suspend fun Repository.doWork2() {
//    withContext(ioDispatcher) {
//        doSomeOtherWork()
//        //this will throw exception
//////        Reason
////        In this example, if an exception is thrown within the withContext(externalScope.coroutineContext),
////        it may not be caught by the CoroutineExceptionHandler defined in the outer scope. Instead, the
////        exception could be rethrown in the parent context, potentially leading to crashes or inconsistent behavior.
//
//        withContext(externalScope.coroutineContext) {
//            throw RuntimeException("Oops!") // This might not be caught by the handler
//        }
//    }

    // how to avoid this problem
    try {
        doSomeOtherWork()

        externalScope.launch {
            throw RuntimeException("Oops!") // This might not be caught by the handler
        }
    } catch (e: Exception) {
        println(e.message)
    }


}