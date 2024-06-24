package com.example.navigationandfunctions.funtions

import android.util.Log

fun <A, B, C> AddAndReturnResult(a: A, b: B, operations: (A, B) -> C): C {
    return operations.invoke(a, b)
}

class FunctionsList {
    fun showResult() {
        Log.d("ResultIs", AddAndReturnResult(3, 4) { a, b ->
            return@AddAndReturnResult a + b
        }.toString())
    }
}

fun showSuspendFunction(onClick :() ->Int?, itemOnClick: ItemOnClick){
    sum.invoke(5,6)
    a.invoke(5)
    val v=compare("amir","rashid")
    sum2.invoke(2,3)
   itemOnClick.invoke(3,4)

}

val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
fun name(s: String): Int { return s.toIntOrNull() ?: 0 }


val a = { i: Int -> i + 1 }

//Lambda expressions and anonymous functions﻿
//Lambda expressions and anonymous functions are function literals. Function literals are functions
// that are not declared but are passed immediately as an expression. Consider the following example:
//max(strings, { a, b -> a.length < b.length })
//The function max is a higher-order function, as it takes a function value as its second argument.
// This second argument is an expression that is itself a function, called a function literal,
// which is equivalent to the following named function:
//fun compare(a: String, b: String): Boolean = a.length < b.length

fun compare(a: String, b: String): Boolean = a.length < b.length

//Lambda expression syntax﻿
//The full syntactic form of lambda expressions is as follows:
val sum1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
//same
val sum2 = { x: Int, y: Int -> x + y }

//Passing trailing lambdas﻿
//According to Kotlin convention, if the last parameter of a function is a function, then a lambda expression passed as the corresponding argument can be placed outside the parentheses:
//val product = items.fold(1) { acc, e -> acc * e }
//Such syntax is also known as trailing lambda.


//You can also give a function type an alternative name by using a type alias:
//typealias ClickHandler = (Button, ClickEvent) -> Unit

typealias ItemOnClick = (Int,Int) -> Unit
