package com.example.statehoistingpreperations.customsaverstoStoreState

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun CustomSaverScreen() {
//    SaveComposableStateDuringRecomposition()
//    SaveComposableStateDuringConfigurationChanges()
    ForListOfObjects()
}


@Composable
fun SaveComposableStateDuringRecomposition(modifier: Modifier = Modifier) {
    // this is used to save state of composable during recomposition
    var simpleData by remember {
        mutableStateOf(User("name", 1))
    }


    Column {
        Text(text = simpleData.name + "    " + simpleData.age)
        OutlinedTextField(
            value = simpleData.name,
            onValueChange = { simpleData = simpleData.copy(name = it) })
    }
}

//for single object
@Composable
fun SaveComposableStateDuringConfigurationChanges(modifier: Modifier = Modifier) {
    // this is used to save state of composable during recomposition
    var simpleData by rememberSaveable(stateSaver = SingleObjectCustomSaver) {
        mutableStateOf(User(name = "Amir", age = 1))
    }


    Column {
        Text(text = simpleData.name + "    " + simpleData.age)
        OutlinedTextField(
            value = simpleData.name,
            onValueChange = { simpleData = simpleData.copy(name = it) })
    }
}

//for List
//here is bug in code because recompose not occur when item iss added
@Composable
fun ForListOfObjects(modifier: Modifier = Modifier) {
    // Example usage with rememberSaveable for a list of Users
    var userList = rememberSaveable(saver = listCustomSaver) {
        mutableListOf(
            User("Alice", 25),
            User("Bob", 30),
            User("Charlie", 28)
        )
    }

    LazyColumn {
        item {
            Button(onClick = {
                userList = userList.toMutableList().apply { add(User("Rashid", 3)) }
                Log.d("UserListLength",userList.size.toString())
            }) {
                Text(text = "AddItem")
            }
        }

        items(userList) { item ->
            Text(text = item.name + "   " + item.age)
        }

    }
}

