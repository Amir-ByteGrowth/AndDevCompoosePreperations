package com.example.statehoistingpreperations.mapper

import kotlin.reflect.full.memberProperties


data class SimpleUser(val id: Int, val name: String, val address: String, val contact: String)
data class SimpleUser1(val id: Int, val name: String, val address: String, val contact: String)

fun UserMainModel.toSimpleUser(): SimpleUser {
    return SimpleUser(
        id = this.id,
        name = (this.fname + ", " + this.lastName),
        address = (this.city + ", " + this.building + ", " + this.flatNo),
        contact = (this.email + ", " + this.phone)
    )
}

//While the example above is very simple (and therefore recommended for most use cases), it still
// involves a bit of boilerplate code. What if we have a class with a lot of fields (maybe hundreds)
// and most of them have to be mapped to the field with the same name in the target class?
//
//In this case, we can think about using the Kotlin Reflection features to avoid writing most of the mapping code.
//
//The mapping function using reflection looks like this:

//it is slow in performance
fun UserMainModel.toSimpleUserUsingReflection() = with(::SimpleUser1) {
    val propertiesByName = UserMainModel::class.memberProperties.associateBy { it.name }
    callBy(parameters.associate { parameter ->
        parameter to when (parameter.name) {
            SimpleUser1::id.name -> id
            SimpleUser1::name.name -> lastName + ", "+fname
            SimpleUser1::address.name -> "$city, $building, $flatNo"
            SimpleUser1::contact.name -> "$email, $phone"
            else -> {
                propertiesByName[parameter.name]?.get(this@toSimpleUserUsingReflection)
            }
        }
    })

}


