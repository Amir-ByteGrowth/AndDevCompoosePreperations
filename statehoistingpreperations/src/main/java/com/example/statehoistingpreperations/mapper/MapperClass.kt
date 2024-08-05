package com.example.statehoistingpreperations.mapper


data class SimpleUser(val id: Int, val name: String, val address: String, val contact: String)

fun UserMainModel.toSimpleUser(): SimpleUser {
    return SimpleUser(
        id = this.id,
        name = (this.name + ", " + this.lastName),
        address = (this.city + ", " + this.building + ", " + this.flatNo),
        contact = (this.email + ", " + this.phone)
    )
}


