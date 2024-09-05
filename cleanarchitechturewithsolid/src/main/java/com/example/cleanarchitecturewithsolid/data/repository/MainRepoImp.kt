package com.example.cleanarchitecturewithsolid.data.repository

import com.example.cleanarchitecturewithsolid.data.local.loggers.FileLogger
import com.example.cleanarchitecturewithsolid.domain.repository.MainRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

/*Solid principle
1- S (single responsibility principle)
   A class should have one, and only one, reason to change, meaning it should have only one responsibility

2- O (Open Close Principle. Open for extension and close for modification principle) (go to Logger class)

3- L (Liskov substitution Principle)
   Subtypes must be substitutable for their base types without altering the correctness of the program.
   or
   parents class should be replaceable with subclasses  without altering the behaviour of parent (go for logger class)

4- I (interface segregation principle)
   Clients (classes) should not be forced to implement functions they do not need.

5- D (Dependency inversion principle)
   High-level modules should not depend on low-level modules. Both should depend on abstractions.
 */



class MainRepoImp(private val auth: FirebaseAuth, private val fileLogger: FileLogger) :
    MainRepository {

    // this method is against (single responsibility rule because for now this class can be changed
    // either by change in authentication way or change in logging way)

    /* override fun login(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            val file = File("name.txt")
            file.appendText(text = e.message.toString())
        }
    }

    */

    // now this full fill first principle because this function needs only one reason to change that is sign in
    override suspend fun login(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            fileLogger.logError(e.message.toString())
        }
    }

}