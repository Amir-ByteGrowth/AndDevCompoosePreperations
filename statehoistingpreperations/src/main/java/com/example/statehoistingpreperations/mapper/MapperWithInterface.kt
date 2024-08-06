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


// First map from List is called
// then map from Mapper is called
fun <F, T> Mapper<F, T>.mapAll(list: List<F>) = list.map { map(it) }
// You could add more collection mapping operations if needed

data class UserEntity(val name: String,val surname:String,)
// Domain model
data class User(
    val name: String,
    val surname: String,
)
// Those can be objects or classes depending on your needs
object UserEntityToModelMapper : Mapper<UserEntity, User> {
    override fun map(from: UserEntity) = User(
        name = from.name,
        surname = from.surname,
    )
}
// Usage
fun main1() {
    val userEntities = listOf(
        UserEntity(name = "Name1", surname = "Surname1"),
        UserEntity(name = "Name2", surname = "Surname2"),
    )
    val users = UserEntityToModelMapper.mapAll(userEntities)
}

//Additionally, if used with dependency injection, you can mock these Mappers to your needs. Their
// initial cost is higher, but in the long run, it’ll pay back.
//I recommend the interface approach when starting a new project as it pays back the most from these 3 approaches.
//Consider using extension functions when working in an existing codebase with little time to refactor.
// If you want to use the interface approach in the future, it’s also straightforward to transition from them.


//1. Extension Functions
//It’s simply about creating an extension function named toModel() , toEntity() etc. Extension functions usually stored in a file by context (for example, file by package), it looks like this:
//// Extension function mapper
//fun UserEntity.toModel() = User(
//    name = name,
//    surname = surname,
//)
//
//fun User.toEntity() = UserEntity(
//    name = name,
//    surname = surname,
//)
//
//// Usage
//fun main() {
//    val userEntity = UserEntity(name = "Name", surname = "Surname")
//    val user = userEntity.toModel() // We get our model
//    user.toEntity() // We can reverse it to get entity
//}
//They’re pretty cool and intuitive, but their most significant drawback is that they’re not generic, meaning we cannot write generic mapping operations(you’ll see what I mean further in this article).
//However, they also have one of the most significant advantages: it’s straightforward to incorporate them into existing codebases because they’re not intrusive. The cost of adding them is minimal.
//2. Constructor
//Another way to map in Kotlin is by usage of additional constructors:
//// Constructor mapper
//data class UserEntity(
//    val name: String,
//    val surname: String,
//) {
//    constructor(model: User) : this(name = model.name, surname = model.surname)
//
//    // We can't create mapper for database to model mapper because
//    // User is in domain layer and doesn't know UserEntity exists
//    // we need this workaround
//    fun toModel() = User(name = name, surname = surname)
//}
//Their biggest drawback is that they’re inside the class, meaning if you need multiple mappers for this class, it’ll grow your class by a lot.
//Additionally, if you wanted to map a class from an external framework, you won’t be able to! It’s not that uncommon to map from them.
//I wouldn’t recommend using them in your projects as they’re intrusive and limited.
//3. Mapper interface
//The idea is simple: create a Mapper interface that every mapping class will implement.
//// Interface mapper
//// since this interface will only have 1 abstract function
//// we can use fun interface
//fun interface Mapper<in From, out To> {
//    fun map(from: From): To
//}
//
//// Those can be objects or classes depending on your needs
//object UserEntityToModelMapper : Mapper<UserEntity, User> {
//    override fun map(from: UserEntity) = User(
//        name = from.name,
//        surname = from.surname,
//    )
//}
//
//object UserToEntityMapper : Mapper<User, UserEntity> {
//    override fun map(from: User) = UserEntity(
//        name = from.name,
//        surname = from.surname,
//    )
//}
//
//// Usage
//fun main() {
//    val userEntity = UserEntity(name = "Name", surname = "Surname")
//    val user = UserEntityToModelMapper.map(userEntity) // We get our model
//    UserToEntityMapper.map(user) // We can reverse it to get entity
//}
//At first glance, this approach has no real advantage over extensions, and we need to write more code.
//However, we’re able to write generic mapping algorithms for things like Collections:
//// First map from List is called
//// then map from Mapper is called
//fun <F, T> Mapper<F, T>.mapAll(list: List<F>) = list.map { map(it) }
//// You could add more collection mapping operations if needed
//
//// Usage
//fun main() {
//    val userEntities = listOf(
//        UserEntity(name = "Name1", surname = "Surname1"),
//        UserEntity(name = "Name2", surname = "Surname2"),
//    )
//    val users = UserEntityToModelMapper.mapAll(userEntities)
//}
//Additionally, if used with dependency injection, you can mock these Mappers to your needs. Their initial cost is higher, but in the long run, it’ll pay back.
//I recommend the interface approach when starting a new project as it pays back the most from these 3 approaches.
//Consider using extension functions when working in an existing codebase with little time to refactor. If you want to use the interface approach in the future, it’s also straightforward to transition from them.
//Thanks for r

