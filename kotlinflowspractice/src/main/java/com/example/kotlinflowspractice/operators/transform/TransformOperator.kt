package com.example.kotlinflowspractice.operators.transform

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

sealed class UserAction {
    data class Click(val x: Int, val y: Int) : UserAction()
    data class Swipe(val length: Int) : UserAction()
    object LongPress : UserAction()
}

private val sourceFlow = flow {
    emit(UserAction.Click(x = 100, y = 200))
    emit(UserAction.Swipe(150))
    emit(UserAction.LongPress)
    emit(UserAction.Click(x = 50, y = 50))
    emit(UserAction.Swipe(50))

}

fun main() = runBlocking {
    sourceFlow.transform { value: UserAction ->
        when (value) {
            is UserAction.Click -> {
                emit("click -> ${value.x}  ${value.y}")
            }

            UserAction.LongPress -> {
                emit("Long Press")
            }

            is UserAction.Swipe -> {
                "swipe - > ${value.length}"
            }
        }
    }.collectLatest { println(it) }
}