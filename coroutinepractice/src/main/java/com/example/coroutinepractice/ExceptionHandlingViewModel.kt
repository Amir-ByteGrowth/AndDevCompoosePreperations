package com.example.coroutinepractice

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class ExceptionHandlingViewModel : ViewModel() {


}

fun main9() {
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }

    runBlocking {
        supervisorScope {
            launch(coroutineExceptionHandler) {
                delay(1000)
                throw RuntimeException("Exception 1")
            }
            launch(coroutineExceptionHandler) {
                throw RuntimeException("Exception 2")
            }
            launch(coroutineExceptionHandler) {
                throw RuntimeException("Exception 3")
            }
        }
    }


}

fun main10() {
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }

    val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)
    scope.launch {
        launch {
            try {
                delay(1000)
                throw RuntimeException("Exception 1")
            } catch (exception: Exception) {
                println(exception.message)
            }

        }
        launch {
            throw RuntimeException("Exception 2")
        }
        launch {
            throw RuntimeException("Exception 3")
        }
    }


}


fun main() = runBlocking {
MainScope()

    supervisorScope {
        try {
            val result1 = async {
                delay(500)
               "\n Job 1 \n"
            }
            val result2 = async {

                delay(1500)
                println("job 2")
                throw RuntimeException("job 2 With exception")

            }
            val result3 = async {
                delay(500)
                "job3"
            }

            println(result1.await()+"    "+result2.await()+"   "+result3.await())

        } catch (excep: Exception) {
            println(excep)
        }
    }

}

