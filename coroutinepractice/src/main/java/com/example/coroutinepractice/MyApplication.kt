package com.example.coroutinepractice

import android.app.Application
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MyApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val exceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable -> println("exception") }
    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO+exceptionHandler)
}