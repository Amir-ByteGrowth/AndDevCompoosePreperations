package com.example.kotlinflowspractice.hotvscoldflow

import android.content.Context
import android.location.Location
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val sourceFlow = flow {
    emit(10)

}

private val channelSourceFlow = channelFlow {
    send(1)
    launch {
        send(10)
        delay(500)
        send("amir")
    }
    launch {
        send(10)
        delay(500)
        send("amir")
    }
    launch {
        send(10)
        delay(500)
        send("amir")
    }
}

var coldFlow = emptyFlow<Int>()

fun main1() = runBlocking {

    channelSourceFlow.collect {
        println(it)
    }
//    channelSourceFlow.collect {
//        println(it)
//    }


}

