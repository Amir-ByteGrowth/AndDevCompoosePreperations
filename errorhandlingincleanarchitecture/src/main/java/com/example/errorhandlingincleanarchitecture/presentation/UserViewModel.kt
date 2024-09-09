package com.example.errorhandlingincleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import com.example.errorhandlingincleanarchitecture.domain.Result
import com.example.errorhandlingincleanarchitecture.domain.UserDataValidator

// we can assume user data validator here as use case

class UserViewModel(private val userDataValidator: UserDataValidator) : ViewModel() {

    fun validateUserPassword(password:String){
        val result =userDataValidator.validatePassword(password)
        when(result){
            is Result.Error -> {
                val error = result.error
                when(error){
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is Result.Success -> TODO()
        }
    }
}