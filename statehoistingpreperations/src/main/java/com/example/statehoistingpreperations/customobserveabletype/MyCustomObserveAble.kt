package com.example.statehoistingpreperations.customobserveabletype

import androidx.lifecycle.Observer

class MyCustomObserveAble<T> {
    private val observers = mutableListOf<(T) -> Unit>()
    private var _value :T? = null

    val value:T?
        get() = null

    fun observe(observer: (T) ->Unit){
        observers.add(observer)
        _value?.let(observer)
    }

    fun setValue(newValue :T){
        _value = newValue
        observers.forEach { it(newValue) }
    }
}