package com.example.coroutinepractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customCoroutineScope = CustomCoroutineScope()
        customCoroutineScope.startTask()
        customCoroutineScope.stop()

    }

    //life cycle aware coroutine
    fun lifeCycleAware() {
        val result = lifecycleScope.async {

        }
        runBlocking {
            result.join()
            result.await()
        }

    }
}


fun main1() = runBlocking {
    delay(1000L)
    println("NewLine Here After 1 second")

    val data = runBlocking { // <- redundant and blocks the thread, do not do that
        println("amir ")// suspending function
    }
}

// coroutinescope doesnot launch child coroutines independently . it means if one of child fail al coroutines stop
fun main2() {
    val coroutineExceptionHandler =
        CoroutineExceptionHandler { corouitineContext, throwable -> println(throwable.toString() + "  throwable ") }
    runBlocking {
        CoroutineScope(coroutineExceptionHandler).launch {
            launch {
                delay(1200)
                println("1st Launch")
            }
            launch {
                throw RuntimeException("While getting Data 2 exception happened")
            }

            launch {
                delay(1000)
                println("3rd Launch")
            }
        }.join()

    }
}


fun main4() {
    val coroutineExceptionHandler =
        CoroutineExceptionHandler { corouitineContext, throwable -> println(throwable.toString() + "  throwable ") }
    runBlocking {
        supervisorScope {

            launch {
                delay(1200)
                println("1st Launch")
            }
            launch {
                try {
                    throw RuntimeException("While getting Data 2 exception happened")
                } catch (e: Exception) {
                    println(e.message.toString())
                }

            }

            launch {
                delay(1000)
                println("3rd Launch")
            }
        }.join()

    }
}


fun main7() = runBlocking {
    val mainScope = MainScope()
    mainScope.launch {
        // Launch a coroutine on the main thread
        delay(1000)
        println("Hello from MainScope")
    }
    mainScope.cancel() // Cancel the scope when done
}


//it works same as job.await, but it did not return any data

fun main8() = runBlocking {
    val job1 = launch {
        delay(1000)
        println("job1")
    }
    val job2 = launch {
        delay(1000)
        println("job2")
    }
    val job3 = launch {
        delay(1000)
        println("job3")
    }
    val job4 = launch {
        delay(15000)
        println("job4")
    }
    joinAll(job1, job2, job3, job4)
    println("outside jobs")
}


fun main() = runBlocking {
    val job1 = launch {
        delay(1000)
        println("job1")
    }.join()
    val job2 = launch {
        delay(1000)
        println("job2")
    }
    val job3 = launch {
        delay(1000)
        println("job3")
    }
    val job4 = launch {
        delay(1500)
        println("job4")
    }.join()

    println("outside jobs")
}

