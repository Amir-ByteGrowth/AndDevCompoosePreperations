package com.example.cleanarchitecturewithsolid.data.local.loggers

import java.io.File
import java.lang.Error

class FileLogger {

    fun logError(error: String){
        val file = File("name.txt")
        file.appendText(text = error)
    }
}