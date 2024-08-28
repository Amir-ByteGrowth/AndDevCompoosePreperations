package com.example.kotlinflowspractice.operators.combining

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

val sourceFlowA = flow {
    emit("Flow A- Emission 1")
    delay(400)
    emit("Flow A - Emission 2")
}

val sourceFlowB = flow {
    emit("Flow B- Emission 1")
    delay(200)
    emit("Flow B - Emission 2")
}

// flatten merge is used when we have flow of flows
val flowOfFlows = flowOf(sourceFlowA, sourceFlowB)

fun main() = runBlocking {
    // will give emits from both flows
//    merge(sourceFlowA, sourceFlowB).collectLatest {
//        println(it)
//    }
    // concourrancy will manage in flight flows means if its value is 1 it will show all emits from
    // flow 1 and then from flow 2. if its values is two it will show concurrency accordingly
//    flowOfFlows.flattenMerge(1).collectLatest { println(it) }

// it is used only when we have flow of flows
    flowOfFlows.flattenMerge().collectLatest { println(it) }
}