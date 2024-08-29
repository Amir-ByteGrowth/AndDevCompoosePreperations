package com.example.kotlinflowspractice.terminaloperator

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.runBlocking

class TerminalOperators {
}

private val sourceFlow = flow {
    emit(1)
    emit(2)
    emit(3)
    emit(4)
    emit(5)
    emit(6)
    emit(7)
}

fun main() = runBlocking {


    sourceFlow.collectLatest {
        // this will not wait for completion of emit it will continue emitting cancel collecting if it is taking more time than emitting  will show 7
//        delay(1000)
//        println(it)
    }
    sourceFlow.collect {
        // this will wait for completion of every emir
//        delay(1000)
//        println(it)
    }
    // they will be collected sequentially

//convert flow to list
    val list = sourceFlow.toList()
//    println(list)

    // convert flow to set
    val sets = sourceFlow.toSet()
//    println(sets)

    //single value
    // flow must have single value if it has more it will crash with exception
//    val singleValue = sourceFlow.single()
//    println(singleValue)

//    it will return null either flow is empty or have more than one emits
    val singleOrNullValue = sourceFlow.singleOrNull()
//    println(singleOrNullValue)


//    val first = sourceFlow.firstOrNull()
    val first = sourceFlow.lastOrNull()
//    println(first)


//    Reduce operator allows performing operation over all items of the flow to reduce them into one item.
//    Here, the result must be the same type as items i.e if you have flow of Integer, you can not get String as a result.
    val reduce = sourceFlow.reduce() { accumulator, value -> accumulator + value }
//    println(reduce)

//    fold work same as reduce differnce is we can give starting value, and it can return any time
//    below example result will be 33 because it is taking 5 as initial value
//    val fold = sourceFlow.fold(5) { accumulator, value -> accumulator + value }
//    this will return amir1234567 because we are passing string so it will concatenate
//    val fold = sourceFlow.fold("amir") { accumulator, value -> accumulator + value }
    // this will give 24
    val fold =
        sourceFlow.fold(2) { accumulator, value -> if (accumulator + value > 5) (accumulator + value) else accumulator }
//    println(fold)

    val count = sourceFlow.count()
//    println(count)


}