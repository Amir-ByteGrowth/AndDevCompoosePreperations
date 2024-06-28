package com.example.statehoistingpreperations.customsaverstoStoreState

import androidx.compose.runtime.saveable.Saver


data class User(var name: String, val age: Int)

val SingleObjectCustomSaver = Saver<User, Map<String , Any>>(
    save = { user ->
        mapOf("name" to user.name, "age" to user.age)
           },
    restore = {state ->
        val name = state["name"] as? String ?: return@Saver null
        val age= state["age"] as? Int ?: return@Saver null
        User(name,age)
    }
)


val listCustomSaver = Saver<List<User>,List<Map<String,Any>>>(
    save = {userList ->
        userList.map {user ->  mapOf("name" to user.name , "age" to user.age) }
    },
    restore = { state ->state.map{userMap ->
        val name = userMap.get("name") as? String ?: return@Saver null
        val age = userMap.get("age") as? Int ?: return@Saver  null
        User(name,age)
    }

    }
)




