package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class WithContextCoroutine {
}

fun main() {

    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {

        runBlocking {
            println(Thread.currentThread().name)
        }

    }
    scope.cancel()

}

suspend fun getData10() {

}