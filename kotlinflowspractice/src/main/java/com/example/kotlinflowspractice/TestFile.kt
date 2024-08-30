package com.example.kotlinflowspractice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

// Function that returns a flow for network calls
fun fetchNetworkData(): Flow<String> = flow {
    // Simulate network call on IO thread

    // Imagine this is your network call
    println("ThreadName  ${Thread.currentThread().name}")
    val result = makeNetworkRequest()

    // code inside this method will be executed on the specified dispatcher here
    withContext(Dispatchers.Main) {
        println("ThreadName ForMain ${Thread.currentThread().name}")
    }

    println("ThreadName after main ${Thread.currentThread().name}")

    emit(result)
    // dispatcher defined here in flowOn will be used for all code outside the with context
}.flowOn(Dispatchers.IO)  // Make sure the flow operates on the IO thread

// Function to perform the network request
suspend fun makeNetworkRequest(): String {
    // Simulating network delay
    delay(2000)
    return "Network data"
}

// Collecting data on the main thread
suspend fun collectNetworkData() {
// if we do not specify Dispatcher here it will use the dispatcher of calling place , for example i am
    // calling this method from main activity using lifecyclescope. by default lifecyclescope use main dispatcher so thread here is used main
    fetchNetworkData()
        .onEach { data ->
            // This is on the main thread, safe for UI updates
            println("ThreadName OnEach ${Thread.currentThread().name}")
            println("Data received: $data")
        }
        .catch { e ->
            // Handle exceptions, if any
            println("Error occurred: ${e.message}")
        }
        .collect {
            println("CollectingThread  ${Thread.currentThread().name}")
            println("Amir $it")
        } // Collect the flow
}


fun main() {

}