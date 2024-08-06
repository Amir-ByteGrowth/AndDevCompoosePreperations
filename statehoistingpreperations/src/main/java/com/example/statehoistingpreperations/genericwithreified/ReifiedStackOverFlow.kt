package com.example.statehoistingpreperations.genericwithreified

class ReifiedStackOverFlow {
}

data class Apple(val name: String = "Apple")
data class Orange(val name: String = "Orange")
data class Banana(val name: String = "Banana")

val fruits = listOf(Apple(), Orange(), Banana(), Orange())

//For filtering the fruit types, we may write an extension function on List<Any> like following:
//In this code, first we filter the types and only take the element if its type matches the given type
//argument. Then we cast each element to the given type argument and return the List. But there are two problems.
//Type checking
//
//While type checking it is T, we are introduced to another error by the compiler: Cannot check for
//    instance of erased type: T. This is another kind of error you may come across due to type erasure.
//
//Type casting
//
//While type casting it as T, we are also given a warning: Unchecked cast: Any to T. The compiler cannot
//confirm the type due to type erasure.

//fun <T> List<Any>.filterFruit(): List<T> {
//    return this.filter { it is T }.map { it as T }          // Error and Warning
//}

inline fun <reified T> List<Any>.filterFruit(): List<T> {
    return this.filter { it is T }.map { it as T }
}



fun main(){
    val orangeList = fruits.filterFruit<Orange>()
    val filteredFruits = fruits.filterFruits<Orange>()
    println(orangeList)
    println(filteredFruits)
}

// reified as arguments

inline fun <reified T> List<Any>.filterFruits():List<T>{
    return filterWithArg<T>()
}

inline fun <reified T> List<Any>.filterWithArg():List<T>{
    return this.filter { it is T }.map { it as T }
}

//Passing reified parameter as an argument
//
//The reified modifier makes it possible for a function to pass the type parameter as a type argument to another function that has reified modifier:
//
//inline fun <reified T> doSomething() {
//    // Passing T as an argument to another function
//    doSomethingElse<T>()
//}

inline fun <reified T> doSomethingElse() { }

//Getting the generic type of the reified type
//
//Sometimes a type argument can be a generic type. For example, List<String> in the function call doSomething<List<String>>(). It's possible to know this entire type, thanks to reification:
//
//inline fun <reified T> getGenericType() {
//    val type: KType = typeOf<T>()
//    println(type)
//}
//Here the typeOf() is a standard library function. The println() function above will print kotlin.collections.List<kotlin.String>, if you call the function as getGenericType<List<String>>(). The KType includes KClass, type argument information and nullability information. Once you know the KType, you can perform reflection on it.
//
//Java interoperability
//
//The inline functions declared without reified type parameters can be called from Java as regular Java functions. But the ones declared with the reified type parameters are not callable from Java.
//
//Even if you call it using the reflection like following:
//
//Method method = YourFilenameKt.class.getDeclaredMethod("doSomething", Object.class);
//method.invoke("hello", Object.class);
//You get the UnsupportedOperationException: This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.
//
//Conclusion
//
//In many cases, the reified types help us get rid of the following errors and warnings:
//
//Error: Cannot use 'T' as reified type parameter. Use a class instead.
//Error: Cannot check for instance of erased type: T
//Warning: Unchecked cast: SomeType