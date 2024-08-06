package com.example.statehoistingpreperations.mapper

import android.util.Log

// Interface mapper
// since this interface will only have 1 abstract function
// we can use fun interface

fun interface Mapper<in from, out To> {
    fun map(from: from): To
}


object SimpleUserFromMain : Mapper<UserMainModel, SimpleUser> {
    override fun map(from: UserMainModel) = SimpleUser(
        name = (from.fname + from.lastName), id = from
            .id, address = from.city, contact = from.email
    )

}

data class SimpleUser2(val id: Int, val name: String)

object SimpleUser2FromMain : Mapper<UserMainModel, SimpleUser2> {
    override fun map(from: UserMainModel) = SimpleUser2(
        name = (from.fname + from.lastName), id = from
            .id
    )
}

fun main() {
    val userMainModel = UserMainModel(
        1,
        "amir",
        "javeed",
        "email",
        "phone",
        "icon",
        "business",
        "building",
        "flatnor",
        "office", "office"
    )

    val simpleUser = SimpleUserFromMain.map(userMainModel)
    println(simpleUser.name)

//    println( SimpleUserFromMain.map(userMainModel).address)
//    Log.d("SimpleUser", SimpleUser2FromMain.map(userMainModel).toString())
}



