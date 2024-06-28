package com.example.statehoistingpreperations.customobserveabletype.withlist

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState


data class User(var name: String, var age: Int)

class UserListObserveAble {
    private val observers = mutableListOf<(List<User>) -> Unit>()
    private var _users: List<User> = emptyList()

    val users: List<User>
        get() = _users

    fun observe(observer: (List<User>) -> Unit) {
        observers.add(observer)
        observer(_users)
    }

    fun updateUsers(newUsers: List<User>) {
        _users = newUsers
        observers.forEach { it(newUsers) }
    }

    fun addMoreUsers(newUsers: List<User>) {
        val oldList = _users.toMutableList()
        oldList.addAll(newUsers)
        _users = oldList
        Log.d("_USersLength", _users.size.toString())
        observers.forEach { it(_users) }
    }
}


@Composable
fun UserListObserveAble.asState(): State<List<User>> {
    return produceState(initialValue = users) {
        val observer: (List<User>) -> Unit = { newUsers -> value = newUsers }
        observe(observer)
        awaitDispose {
            // Add any necessary cleanup here if needed
        }
    }
}