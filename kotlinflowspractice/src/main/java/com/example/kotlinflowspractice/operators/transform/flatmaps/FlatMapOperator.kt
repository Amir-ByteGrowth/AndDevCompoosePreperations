package com.example.kotlinflowspractice.operators.transform.flatmaps

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.runBlocking

fun fetchOrders(userId: Int) = flow {
    emit("Order 1 for User $userId")
    delay(100)
    emit("Order 2 for User $userId")
}

val userIdFlow = flow {
    emit(1)
    delay(50)
    emit(2)
    delay(50)
    emit(3)
}

// there are three types of flat map operators flatmapconcat, flatmapmerge, flatmaplatest
//flatmap concat
// if we want to maintain the sequence of execution
@OptIn(ExperimentalCoroutinesApi::class)
val concat = userIdFlow.flatMapConcat { fetchOrders(it) }

// merge flat map
// it will execute code concurrently it does not care about sequence
@OptIn(ExperimentalCoroutinesApi::class)
val merge = userIdFlow.flatMapMerge { fetchOrders(it) }

// flatmap latest
// it cancels the previous flow if the new flow is launched
val latest = userIdFlow.flatMapLatest { fetchOrders(it)  }
//latest-> Order 1 for User 1
//latest-> Order 1 for User 2
//latest-> Order 1 for User 3
//latest-> Order 2 for User 3

fun main() = runBlocking {
//    concat.collectLatest { println(it) }
//    merge.collectLatest { println("merge-> $it") }
    latest.collectLatest { println("latest-> $it") }

}