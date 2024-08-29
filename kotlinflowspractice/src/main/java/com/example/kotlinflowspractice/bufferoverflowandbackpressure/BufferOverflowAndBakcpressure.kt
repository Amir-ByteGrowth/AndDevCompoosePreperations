package com.example.kotlinflowspractice.bufferoverflowandbackpressure

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// if we use bufferoverflow suspend it will suspend until consumer consumed it it will copnsume  as max as possible emission

//this startagey will buffer 30 oldest or latest
private val sourceFlow = flow { repeat(1000){emit(it)} }.buffer(30, onBufferOverflow = BufferOverflow.DROP_OLDEST)

fun main()= runBlocking {
    sourceFlow.collect {
        delay(1000)
        println(it)
    }
}