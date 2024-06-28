package com.example.statehoistingpreperations.customsaverstoStoreState

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier



//data class User(val name: String, val age: Int)
// for custom list objects
private fun userListSaver() = listSaver<SnapshotStateList<User>, Map<String, Any>>(
    save = { stateList ->
        stateList.map { user ->
            mapOf(
                "name" to user.name,
                "age" to user.age
            )
        }
    },
    restore = { restoredList ->
        restoredList.map {
            User(
                name = it["name"] as String,
                age = it["age"] as Int
            )
        }.toMutableStateList()
    }
)

@Composable
fun rememberMutableStateListOfUsers(vararg users: User): SnapshotStateList<User> {
    return rememberSaveable(saver = userListSaver()) {
        users.toList().toMutableStateList()
    }
}

// for single item
@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)


@Composable
fun MyListComposable(modifier: Modifier = Modifier) {
    val names = rememberMutableStateListOf<User>()
    // Remember the latest state
    val latestNames = rememberUpdatedState(names)
    Log.d("MyListComposable", "Body")
//    LaunchedEffect(Unit) {
//        latestNames.value.add("Bill")
////        Log.d("MyListComposable","LaunchedEffect")
//    }

    // LaunchedEffect with a constant key
    // Flag to track if the side effect has been executed
    var isInitialized by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        if (!isInitialized) {
            names.add(User("Amir",1))
            isInitialized = true
        }
    }


    ListContent(namesList = names) {
        names.add(User("Amir",2))
        Log.d("UserListLength", names.size.toString())
    }


}

@Composable
fun ListContent(namesList: List<User>, onClick: () -> Unit) {
    LazyColumn {
        item {
            Button(onClick = {
                onClick.invoke()

            }) {
                Text(text = "AddItem")
            }
        }

        items(namesList) { item ->
            Text(text = item.name)
        }

    }
}


@Composable
fun ForListOfUsers() {
    val userList = rememberMutableStateListOfUsers(
        User("Alice", 25),
        User("Bob", 30),
        User("Charlie", 28)
    )

    Column {
        Button(onClick = { userList.add(User("Rashid", 3)) }) {
            Text(text = "Add Item")
        }

        LazyColumn {
            items(userList) { user ->
                Text(text = "${user.name} - ${user.age}")
            }
        }
    }
}