package com.example.statehoistingpreperations.customobserveabletype.withlist

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

class CustomObserveAbleListScreen {
}

@Composable
fun UserListComposable(userListObservable: UserListObserveAble = UserListObserveAble()) {
    val userList by userListObservable.asState()
    Log.d("UserListComposable", userList.size.toString())
    Column {
        Button(onClick = {
            userListObservable.addMoreUsers(
                listOf(
                    User("Amir", 1),
                    User("Amir", 1),
                    User("Amir", 1),
                    User("Amir", 1)
                )
            )
        }) {
            Text(text = "Add Items")
        }
        userList.forEach { user ->
            Text(text = "${user.name}, ${user.age}")
        }
    }
}