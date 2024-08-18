package com.example.coroutinepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class MainViewModel :ViewModel() {






    fun main () = runBlocking {

        delay(1000L)
        println("NewLine Here After 1 second")
    }


    // Define a job that will be canceled when the ViewModel is cleared
    private val viewModelJob = Job()

    // Create a custom scope with the job and a dispatcher
    private val customScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    // Example of launching a coroutine in the custom scope
    fun loadData() {
        customScope.launch {
            // Perform some background work
            println("Loading data in custom scope")
            // Simulate network call or heavy computation
        }
    }

    // Override onCleared to cancel the job when the ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        customScope.cancel()
        viewModelJob.cancel() // This will cancel all coroutines in the custom scope
    }



}