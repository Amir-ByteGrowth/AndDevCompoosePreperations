package com.example.statehoistingpreperations.genericwithreified


fun <T> displayValue(value: T) {
    println(value)
//    println("Type of T: ${T::class.java}")
//    Cannot use ‘T’ as reified type parameter. Use a class instead.
//    The reason is, like java, in kotlin also, type information will be erasured during compile time
}

// to solve the above issue we will pass type to generic function
fun <T> displayValue(classType: Class<T>, value: T) {
    println(value)
    println("Type of T: $classType")
}

fun main() {
    displayValue("Generics")
    displayValue(10)
    displayValue(String::class.java, "Generics")
    displayValue(Int::class.java, 10)
    displayValue1<String>("Generics")
    displayValue1<Int>(10)
    val intMarks = displayData<Int>(60)
    val stringMessage = displayData<String>(95)
    println("$intMarks")
    println(stringMessage)
}

//The keyword reified enables you to access the type of info at runtime that should have been erased
// during code compilation. The reified keyword uses an inline function to perform this task.
//If a function is marked as inline , then wherever the function is called, the compiler will paste
// the whole body of the function there.

inline fun <reified T> displayValue1(value: T) {
    println(value)
    println("Type of T: ${T::class.java}")
}

//fun main() {
//    displayValue<String>("Generics")
//    displayValue<Int>(10)
//}

//other examples for reified
//below code will throw exception due to overloading with same signature
//fun displayData(marks: Int): Int {
//    return marks
//}
//
//fun displayData(marks: Int): String {
//    return "Congratulations! you scored more than 90%";
//}

//To solve this, We can refactor the above functions by using reified keyword
inline fun<reified T> displayData(marks: Int): T {
    return when (T::class) {
        Int::class -> marks as T
        String::class -> "Congratulations! you scored more than 90%" as T
        else -> "Please enter valid type" as T
    }
}


//Limitations
//One of the primary constraints is that reified type parameters can only be used with inline functions.
// This linkage is due to how reified types preserve type information by inlining the function code
// at the call site.
//Reified type parameters cannot represent some complex or non-denotable types. For instance, you
// cannot have a reified parameter for types like List<Int> or Array<String> directly.
//inline fun <reified T> printList(list: List<T>) {
//    // This won't work as expected for types like List<Int>
//}
//Reified type parameters can’t be used in certain scenarios, like as type arguments for non-inline
// functions, in object declarations, or as property types in a class.
//class SampleClass<reified T> { ... } //This will not work