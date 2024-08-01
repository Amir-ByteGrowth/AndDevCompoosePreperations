package com.example.compoosepreperations.ui.screens.mutexvsimmutex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MutexViewModel : ViewModel() {
    val mutex = Mutex()
    var sharedResource = 0

    suspend fun incrementResource() {
        mutex.withLock {
            // Critical section
            val temp = sharedResource
            delay(100) // Simulating some work
            sharedResource = temp + 1
            println("Resource incremented to $sharedResource")
        }
    }

    suspend fun incrementResource1() {
        mutex.withLock {
            // Critical section
            val temp = sharedResource
            delay(100) // Simulating some work
            sharedResource = temp + 1
            println("1 Resource incremented to $sharedResource")
        }
    }

//    if we run these function output will be
//    I  Resource incremented to 76
//    2024-08-01 17:20:13.365 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 77
//    2024-08-01 17:20:13.471 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 78
//    2024-08-01 17:20:13.579 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 79
//    2024-08-01 17:20:13.685 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 80
//    2024-08-01 17:20:13.791 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 81
//    2024-08-01 17:20:13.897 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 82
//    2024-08-01 17:20:14.003 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 83
//    2024-08-01 17:20:14.108 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 84
//    2024-08-01 17:20:14.214 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 85
//    2024-08-01 17:20:14.319 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 86
//    2024-08-01 17:20:14.427 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 87
//    2024-08-01 17:20:14.533 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 88
//    2024-08-01 17:20:14.638 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 89
//    2024-08-01 17:20:14.743 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 90
//    2024-08-01 17:20:14.847 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 91
//    2024-08-01 17:20:14.953 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 92
//    2024-08-01 17:20:15.060 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 93
//    2024-08-01 17:20:15.166 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 94
//    2024-08-01 17:20:15.271 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 95
//    2024-08-01 17:20:15.377 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 96
//    2024-08-01 17:20:15.484 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 97
//    2024-08-01 17:20:15.590 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 98
//    2024-08-01 17:20:15.695 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 99
//    2024-08-01 17:20:15.802 28173-28173 System.out              com.example.compoosepreperations     I  Resource incremented to 100
//    2024-08-01 17:20:15.930 28173-28173 System.out              com.example.compoosepreperations     I  1 Resource incremented to 101
//    2024-08-01 17:20:16.036 28173-28173 System.out              com.example.compoosepreperations     I  1 Resource incremented to 102
//    2024-08-01 17:20:16.142 28173-28173 System.out              com.example.compoosepreperations     I  1 Resource incremented to 103
//    2024-08-01 17:20:16.250 28173-28173 System.out              com.example.compoosepreperations     I  1 Resource incremented to 104






    //    suspend fun incrementResource() {
//
//            // Critical section
//            val temp = sharedResource
//            delay(100) // Simulating some work
//            sharedResource = temp + 1
//            println("Resource incremented to $sharedResource")
//
//    }
//
//    suspend fun incrementResource1() {
//
//            // Critical section
//            val temp = sharedResource
//            delay(100) // Simulating some work
//            sharedResource = temp + 1
//            println("1 Resource incremented to $sharedResource")
//
//    }




    //if we run these function multiple coroutine will run at a time  out put will look like this
//    Resource incremented to 9
//    2024-08-01 17:14:56.547 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.548 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.549 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.549 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.549 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.550 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.550 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 9
//    2024-08-01 17:14:56.646 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.647 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.648 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.649 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.649 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.650 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.651 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.651 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.652 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.653 28023-28023 System.out              com.example.compoosepreperations     I  Resource incremented to 10
//    2024-08-01 17:14:56.756 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.756 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.757 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.757 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.757 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.757 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.758 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.758 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.758 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.759 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 11
//    2024-08-01 17:14:56.856 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.856 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.858 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.858 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.858 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.859 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.859 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.859 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.860 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.860 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 12
//    2024-08-01 17:14:56.958 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.959 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.960 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.960 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.961 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.962 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.963 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.963 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.964 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:56.965 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 13
//    2024-08-01 17:14:57.061 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.062 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.062 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.063 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.064 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.065 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.065 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.066 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.067 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.067 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 14
//    2024-08-01 17:14:57.164 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.165 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.166 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.166 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.167 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.168 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.169 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.169 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.170 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.171 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 15
//    2024-08-01 17:14:57.266 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.267 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.268 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.269 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.270 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.270 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.271 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.272 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.272 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.273 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 16
//    2024-08-01 17:14:57.370 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.371 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.372 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.373 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.374 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.374 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.375 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.376 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.376 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.377 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 17
//    2024-08-01 17:14:57.474 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.475 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.476 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.476 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.477 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.478 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.479 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.479 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.480 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.481 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 18
//    2024-08-01 17:14:57.575 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.576 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.577 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.578 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.578 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.579 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.580 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.580 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.581 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.582 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 19
//    2024-08-01 17:14:57.678 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.680 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.682 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.684 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.685 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.687 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.688 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.689 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.690 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20
//    2024-08-01 17:14:57.691 28023-28023 System.out              com.example.compoosepreperations     I  1 Resource incremented to 20



    fun main() = viewModelScope.launch {
        val jobs = List(10) {
            launch {
                repeat(10) {
                    incrementResource()
                }
            }
        }
        jobs.forEach { it.join() }

        val jobs1 = List(10) {
            launch {
                repeat(10) {
                    incrementResource1()
                }
            }
        }

        jobs1.forEach { it.join() }
    }
}