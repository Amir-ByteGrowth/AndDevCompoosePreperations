package com.example.coroutinepractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomJobViewModel : ViewModel() {

    private val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.IO + job)

    fun launchCoroutine() {
        scope.launch {
            delay(1000)
            println("Coroutine Launched")
        }
    }


    /**
     * Heavy operation that cannot be done in the Main Thread
     */
    fun launchDataLoad() {
        viewModelScope.launch {
            sortList()
            // Modify UI
        }
    }

    private suspend fun sortList() = withContext(Dispatchers.Default) {
        // Heavy work
    }

}