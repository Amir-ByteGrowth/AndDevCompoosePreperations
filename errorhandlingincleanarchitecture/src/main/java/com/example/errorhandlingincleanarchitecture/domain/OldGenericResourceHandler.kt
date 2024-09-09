package com.example.errorhandlingincleanarchitecture.domain

sealed class OldGenericResourceHandler<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : OldGenericResourceHandler<T>(data)
    class Error<T>(data: T? = null, message: String) : OldGenericResourceHandler<T>(data, message)
}
/*this class is used only for showing the error message to the users
    there are two problems in this class
        1- this class don't let us distinguish between error type ( is it network error, request time out or something else)
         if we want to do something specifically for some codes in viewmodel we have to compare messages for this.
        2- if we want to show different messages against different errors in ui what what we will do?
our repository should not decide what message to show if there is some error. this is not domain logic
or data layer should decide what to show. we will be using string resources for different messages and we can
add translation for these errors after that it is not possible in both layers .
we will remove this error creating advance error handling class
 */