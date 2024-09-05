package com.example.cleanarchitecturewithsolid.data.local.loggers

import java.io.File

//by default classes are final in kotlin. we need to write open with the
// now this is open for extension and closed for modification
open class FileLogger {
    // for optional over ride make this function open
    open fun logError(error: String) {
        val file = File("name.txt")
        file.appendText(text = error)
    }
// abstract function can be declared only within abstract class
    // if function is declared it must be implemented in inheriting class
//    abstract  fun logError(error: String)

}

// these classes are already fulfilling the third principle
//parents class should be replaceable with subclasses  without altering the behaviour of parent
// by creating this log error message from above class will not be effected
class CustomErrorFilerLogger : FileLogger() {
    //over ride is not compulsory if we use open in parent class
    override fun logError(error: String) {
        val file = File("custom_error_name.txt")
        file.appendText(text = error)
    }
}

class CustomErrorFilerLogger2 : FileLogger() {
    //over ride is not compulsory if we use open in parent class
    override fun logError(error: String) {
        val file = File("custom_error_name.txt")
        file.appendText(text = error)
    }
}

// here we will break the third rule it is not replaceable to parent class. if we use above classes they have general logError method
// when ever the  loggError method will be called it will be called according to mentioned class . but in the following case
// it hase loggError method from parent class and LogErrorWithNewName its own.
class CustomErrorBreakThirdRule:FileLogger(){
    // this is not replaceable with
     fun logErrorWithNewName(error: String) {

    }
}


/// Interface segregation principle ( classes should not force to implement methods they don't need)
interface FileLoggerI {

    // in interface all functions are open by default
    fun logError(error: String) {
        val file = File("name.txt")
        file.appendText(text = error)
    }

    // if no definition is not given then function is abstract by default we have to override this compulsory where we implement this interface
    // if it is not needed we can avoid this either by giving definition here or creating other class or interface for implementing fourth principle
//    fun printLogs()

}

/*abstract class FileLoggerI {

    // in abstract class all functions are not open by default if you want to override mention
    them open or you have to give abstract function it will not have any bod
    fun logError(error: String) {
        val file = File("name.txt")
        file.appendText(text = error)
    }
//  abstract  fun logError(error: String)
}*/

// interface don't need constructor for implementation
class ErrorLogger : FileLoggerI {
    override fun logError(error: String) {

    }
}

