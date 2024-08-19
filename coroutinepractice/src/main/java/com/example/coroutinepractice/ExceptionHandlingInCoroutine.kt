package com.example.coroutinepractice

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.io.IOException

class ExceptionHandlingInCoroutine {
}
// this is father of all coroutine exception handling classes in this package


//Not* However, when we surround a failing coroutine with the coroutineScope{} scoping function, something interesting happens

fun mainCheck1() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
//           exception will be caught perfectly here
//            throw IllegalStateException("Illegal Exception")
//            if we launch an other coroutine here it will crashed
            launch {
//                In this case the exception will not be handled & the app will be crashed.
//                The reason for this is that the inner coroutine completes exceptionally & the
//                        coroutine fails. But this seems confusing. it will crash for both asynch and launch
                throw IllegalStateException("Illegal Exception")
//                If a Coroutine doesn’t handle exceptions by itself with a try-catch clause, the exception isn’t re-thrown and can’t, therefore, be handled by an outer try-catch clause. Instead, the exception is “propagated up the job hierarchy”.
            }
        } catch (e: Exception) {
            println("Handled" + e.message)
        }
    }
}

//To handle the above case we need to add CoroutineExceptionHandler as shown in below code.
//it will work for both asynch and launch untill we use await with deffered object
//When using CoroutineExceptionHandler, if one coroutine fails, then all child coroutines will get cancelled by default.

fun mainCheck2() {
    val exceptionHandlingInCoroutine = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    scope.launch {
        launch {
            delay(500)
            println("job 1")
        }
        launch {
            delay(800)
            println("job 2")
        }
        launch {
            delay(1000)
            println("job 3")
        }

        launch {
            println("job 4")
            throw IllegalStateException("Illegal Exception")
//                If a Coroutine doesn’t handle exceptions by itself with a try-catch clause, the exception isn’t re-thrown and can’t, therefore, be handled by an outer try-catch clause. Instead, the exception is “propagated up the job hierarchy”.
        }

    }
}
fun mainThink() {
    val exceptionHandlingInCoroutine = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    coroutineScope.launch(Dispatchers.IO ) {
        val deferredResult = coroutineScope.async {
            throw IllegalStateException("Error thrown in asyncAwaitWithExHandler")
        }
        val deferredResult2 = coroutineScope.async {
            throw IllegalStateException("Error thrown in asyncAwaitWithExHandler")
        }
        val deferredResul3 = coroutineScope.async {
            throw IllegalStateException("Error thrown in asyncAwaitWithExHandler")
        }

        deferredResult.await()
        deferredResult2.await()
        deferredResul3.await()
    }
}


//here it will not crash because exception handler is given to parent coroutine launcher
//if we give to inner launcher it will crash
fun mainCheck3() {
    val exceptionHandlingInCoroutine = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
//    coroutineScope.launch(Dispatchers.IO ) {
//        val deferredResult = coroutineScope.async {
//            throw IllegalStateException("Error thrown in asyncAwaitWithExHandler")
//        }
//
//        deferredResult.await()
//    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    scope.launch {
        val d1 = async {
            delay(500)
            println("job 1")
        }
        val d2 = async {
            delay(800)
            println("job 2")
        }
        val d3 = async {
            delay(1000)
            println("job 3")
        }

        val d4 = async {
            println("job 4")
            throw IllegalStateException("Illegal Exception")
//                If a Coroutine doesn’t handle exceptions by itself with a try-catch clause, the exception isn’t re-thrown and can’t, therefore, be handled by an outer try-catch clause. Instead, the exception is “propagated up the job hierarchy”.
        }
        d1.await()
        d2.await()
        d3.await()
        d4.await()
    }
}

//Exception Handling properties of supervisorScope{ }
//We have seen that in all the above cases if 1 coroutine fails, all other coroutines fail too. What if we need other coroutines to continue. Here SupervisorScope comes to the rescue.
//SupervisorScope creates a new independent nested sub-scope. It doesn’t re-throws exceptions like coroutineScope, neither it propagate exceptions to the parent coroutine scope.
//We can add CoroutineExceptionHandler or use try-catch to handle exceptions inside SupervisorScope.

fun mainCheck6() {
    val exceptionHandlingInCoroutine = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    scope.launch {
        val job1 = launch {
            println("starting Coroutine 1")
        }

        supervisorScope {
            val job2 = launch {
                println("starting Coroutine 2")
                throw java.lang.IllegalStateException("Exception in Coroutine 2 inside supervisor scope")
            }

            val job3 = launch {
                delay(1500)
                println("starting Coroutine 3")
            }
        }
    }
}

//For async/await we need to add try-catch to make other coroutines work if one fails like below
fun asyncSupervisorScopeWithTryCatch() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch() {
        val job1 = launch {
            println("starting Coroutine 1")
        }

        supervisorScope {
            val job4 = async() {
                println("starting Coroutine 4")
                throw java.lang.IllegalStateException("Exception in Coroutine 4 inside supervisor scope")
            }

            val job5 = async() {
                println("starting Coroutine 5")
                throw java.lang.IllegalStateException("Exception in Coroutine 5 inside supervisor scope")
            }

            try {
                job4.await()
            } catch (e: Exception) {
                println("Handle $e in try/catch")
            }

            try {
                job5.await()
            } catch (e: Exception) {
                println("Handle $e in try/catch")
            }
        }
    }
}

//For withContext, we can use the same above ways to handle exceptions, just the coroutine builder will be changed & everything inside withContext will be executed sequentially.


//We can also use try-catch with async/await to handle exceptions, but to handle exceptions with try-catch, we need to add a try-catch block on await method & not on the async method. So Let’s check the code below

fun mainCheck5() {
    val exceptionHandlingInCoroutine = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.message)
    }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    scope.launch {

        val deferredResult = async {
            throw IllegalStateException("Error thrown in tryCatchWithAsyncAwait")
        }

        try {
            deferredResult.await()
        } catch (e: Exception) {
            println("Handle Exception $e")
        }
    }
}


//Exception handling properties of coroutineScope{ }
//When we talked about try-catch with Coroutines at the beginning of this article, I told you that a failing coroutine is propagating its exception up the job hierarchy instead of re-throwing it and therefore, an outer try-catch is ineffective.
//However, when we surround a failing coroutine with the coroutineScope{} scoping function, something interesting happens

//The scoping function coroutineScope{} re-throws exceptions of its failed child coroutines instead of propagating them up the job hierarchy, which allows us to handle exceptions of failed coroutines with try-catch
// it will crash when we remove try catch here
fun mainCheck() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
            coroutineScope {
                launch {
                    throw IllegalStateException("IllegalStateException in nested coroutine")
                }

                // ----------- OR We can also write async/await inside coroutineScope
                val deferredResult1 = async(Dispatchers.IO) {
                    throw IllegalStateException("Error thrown in async")
                }
                deferredResult1.await()

            }
        } catch (e: Exception) {
            println("Handle $e in try/catch")
            // catch error
        }
    }
}


//if we use supervisor scope with asynch it will catch exception in try catch and application will not crash
suspend fun mainCheckWithSuperViserScopeAndAsynch() {
    val scope = CoroutineScope(Dispatchers.IO)
    supervisorScope {
        try {
//           exception will be caught perfectly here
//            throw IllegalStateException("Illegal Exception")
//            if we launch an other coroutine here it will crashed
            async {
//                In this case the exception will not be handled & the app will be crashed.
//                The reason for this is that the inner coroutine completes exceptionally & the
//                        coroutine fails. But this seems confusing. it will crash for both asynch and launch
                throw IllegalStateException("Illegal Exception")
            }.await()
        } catch (e: Exception) {
            println("Handled" + e.message)
        }
    }
}

//if we launch coroutine using coroutine scopes and some of it throw exception  and we are using try catch for all launch then it will not cancel other cooroutine and handle exception gracefully

suspend fun getDataWithCoroutineAndException() {
    coroutineScope {
        launch {
            delay(500)
            println("Job1")
        }
        launch {
            delay(1000)
            println("Job2")
        }
        launch {
            try {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
            } catch (e: Exception) {
                println(e.message)
            }

        }
        launch {
            delay(2000)
            println("Job4")
        }
    }
}

suspend fun getDataWithSupervisorScope() {
    coroutineScope {
        launch {
            delay(500)
            println("Job1")
        }
        launch {
            delay(1000)
            println("Job2")
        }
        launch {
            try {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
            } catch (e: Exception) {
                println(e.message)
            }

        }
        launch {
            delay(2000)
            println("Job4")
        }
    }
}

//if we use try catch in all coroutine it will catch exception in separately and and do not disturb other coroutine
suspend fun getDataWithSeperateTryCatch() {
    coroutineScope {
        val job1 = launch {
            delay(500)
            println("Job1")
        }
        val job2 = launch {
            delay(1000)
            println("Job2")
        }
        val job3 = launch {
            try {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
                // if we cancel here it will  cancel  other coroutines
//                cancel()
            } catch (e: Exception) {
                println(e.message)
            }

        }
        val job4 = launch {
//            ensureActive() will also not work here
//            ensureActive()
            delay(2000)
            println("Job4")
        }
        //we have to manualy cancel here

        joinAll(job1, job2, job3, job4)

    }
}

//application will crash with exception
suspend fun getDataWithParentTryCatch() {

    coroutineScope {
        try {
            val job1 = launch {
                delay(500)
                println("Job1")
            }
            val job2 = launch {
                delay(1000)
                println("Job2")
            }
            val job3 = launch {

                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
                // if we cancel here it will  cancel  other coroutines
//                cancel()


            }
            val job4 = launch {
//            ensureActive() will also not work here
//            ensureActive()
                delay(2000)
                println("Job4")
            }
            //we have to manualy cancel here

            joinAll(job1, job2, job3, job4)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

//to stop this crash we will coroutine exception handler because launch will through exception to
// its parent that to its parent and exception propogate untill root
suspend fun getDataWithCoroutineScopeAndCoroutineExceptionHandler() {
    val exceptionHandlingInCoroutine =
        CoroutineExceptionHandler { coroutineContext, throwable -> print(throwable.localizedMessage) }
    val scope = CoroutineScope(Dispatchers.Default + exceptionHandlingInCoroutine)

    scope.launch {
        try {
            val job1 = launch {
                delay(500)
                println("Job1")
            }
            val job2 = launch {
                delay(1000)
                println("Job2")
            }
            val job3 = launch {

                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
                // if we cancel here it will  cancel  other coroutines
//                cancel()


            }
            val job4 = launch {
//            ensureActive() will also not work here
//            ensureActive()
                delay(2000)
                println("Job4")
            }
            //we have to manualy cancel here

            joinAll(job1, job2, job3, job4)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

//or
//we can use try catch inside all launch in this way all coroutines will work instead of exception using coroutine scoping function

fun mainChecking() = runBlocking {
//    getData()
//    getDataWithSuperVisorScopeAndAsynch()
//    getDataWithAsynch()
//    getDataWithLaunchAndTryCathAllInside()

}

suspend fun getData() {

    coroutineScope {
        val job1 = launch {
            delay(500)
            println("Job1")
        }
        val job2 = launch {
            delay(1000)
            println("Job2")
        }
        val job3 = launch {
            try {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
            } catch (e: Exception) {
                println(e.message)
            }


        }
        val job4 = launch {

            delay(2500)
            println("Job4")
        }
        joinAll(job1, job2, job3, job4)

    }
}

suspend fun getDataWithCoroutineAsynch() {

    coroutineScope {
        val job1 = async {
            delay(500)
            println("Job1")
        }
        val job2 = async {
            delay(1000)
            println("Job2")
        }
        val job3 = async {
            try {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
            } catch (e: Exception) {
                println(e.message)
            }


        }
        val job4 = async {

            delay(2500)
            println("Job4")
        }
        joinAll(job1, job2, job3, job4)

    }
}

// now the app will not crash and exception will be caught in try catch section if we use super visor scope with asynch
// if we use launch here application will crash again
suspend fun getDataWithSuperVisorScopeAndAsynch() {

    supervisorScope {
        try {
            val job1 = async {
                delay(500)
                println("Job1")
            }
            val job2 = async {
                delay(1000)
                println("Job2")
            }
            val job3 = async {
                delay(1500)
                println("Job3")
                throw RuntimeException("Run Time Exception")
            }
            val job4 = async {
                delay(2000)
                println("Job4")
            }
            job1.await()
            job2.await()
            job3.await()
            job4.await()

        } catch (e: RuntimeException) {
            println("Caught exception: ${e.message}")
        }
    }
}


/// if we use try catch inside all launch method in coroutine scope it will let other coroutine work independently and give exception in catch section
fun getDataWithLaunchAndTryCathAllInside() {
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        launch {
            try {
                delay(500)
                println("job 1")
            } catch (e: Exception) {
                println(e.message)
            }

        }
        launch {
            try {
                delay(800)
                println("job 2")
            } catch (e: Exception) {
                println(e.message)
            }
        }
        launch {
            try {
                delay(1000)
                println("job 3")
            } catch (e: Exception) {
                println(e.message)
            }
        }

        launch {
            try {
                println("job 4")
                throw IllegalStateException("Illegal Exception")
            } catch (e: Exception) {
                println(e.message)
            }

        }

    }
}


/// coroutine exception handler for individual job
//if we dont handle exception in parent and we want to handle exception in all launching method application will crash
fun mainCoroutineExceptionHandlerWithLaunchWithoutParentExceptionHandler() {

    val exceptionHandlingInCoroutine =
        CoroutineExceptionHandler { coroutineContext, throwable -> println(throwable.message) }
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        val job1 = launch(exceptionHandlingInCoroutine) {
            // Simulate a network request
            throw IllegalStateException("Job1 Network request failed")
        }

        val job2 = launch(exceptionHandlingInCoroutine) {
            // Another network request
            throw IOException(" job2 Network error")
        }

        // Wait for both jobs to complete
        job1.join()
        job2.join()
    }


}

//ti avoid this issue we have to give exception handler to parent scope
fun mainCoroutineExceptionHandlerWithLaunch() {

    val exceptionHandlingInCoroutine =
        CoroutineExceptionHandler { coroutineContext, throwable -> println(throwable.message) }
    val scope = CoroutineScope(Dispatchers.IO + exceptionHandlingInCoroutine)
    scope.launch {
        val job1 = launch {
            // Simulate a network request
            throw IllegalStateException("Job1 Network request failed")
        }

        val job2 = launch {
            // Another network request
            throw IOException(" job2 Network error")
        }

        // Wait for both jobs to complete
        job1.join()
        job2.join()
    }


}

/// supervisor job in context of corotuine scope

fun mainSuperVisorJob() {

    val scope = CoroutineScope(SupervisorJob())
    // all coroutines are completed but the crash occured
//    In this case, if child#1 fails, neither scope nor child#2 will be cancelled.
//    scope.launch {
//
//        println("First job")
//       throw  IllegalStateException("job1 Illegal Exception")
//    }
//    scope.launch {
//
//        println("Second job")
//        throw RuntimeException("job2 Run time exception")
//    }

    val scopeWithExceptionHandler = CoroutineScope(SupervisorJob()+CoroutineExceptionHandler{coroutineContext, throwable -> println(throwable.message)  })
    // all coroutines are completed but the crash occured
//    In this case, if child#1 fails, neither scope nor child#2 will be cancelled.
    scopeWithExceptionHandler.launch {

        println("First job")
       throw  IllegalStateException("job1 Illegal Exception")
    }
    scopeWithExceptionHandler.launch {

        println("Second job")
        throw RuntimeException("job2 Run time exception")
    }


    // now we will use this scope as parent to launch more than one job
//    now when chid one cancelled with exception it will cancel second job and its scop as well
//    scope.launch {
//        launch {
//            println("First job")
//            throw  IllegalStateException("job1 Illegal Exception")
//        }
//        launch {
//            println("Second job")
//            throw RuntimeException("job2 Run time exception")
//        }
//    }

//    crash will be handled by this way but scope and remaining courtines will be cancelled
//    val secondScope = CoroutineScope(SupervisorJob()+CoroutineExceptionHandler{coroutineContext, throwable -> println(throwable.message) })
//    secondScope.launch {
//        launch {
//            println("First job")
//            throw  IllegalStateException("job1 Illegal Exception")
//        }
//        launch {
//            println("Second job")
//            throw RuntimeException("job2 Run time exception")
//        }
//    }
//

//    now both coroutine will both will throw exception both exception will be handled gracefully
//    val thirdScope = CoroutineScope(Dispatchers.IO)
//    thirdScope.launch(CoroutineExceptionHandler{coroutineContext, throwable -> println(throwable.message) }) {
//        supervisorScope {
//
//            launch {
//                println("First job")
//                throw IllegalStateException("job1 Illegal Exception")
//            }
//            launch {
//                println("Second job")
//                throw RuntimeException("job2 Run time exception")
//            }
//        }
//    }


}