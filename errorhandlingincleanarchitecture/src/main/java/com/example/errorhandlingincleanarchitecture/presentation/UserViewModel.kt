package com.example.errorhandlingincleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.errorhandlingincleanarchitecture.domain.AuthRepository
import com.example.errorhandlingincleanarchitecture.domain.DataError
import com.example.errorhandlingincleanarchitecture.domain.Result
import com.example.errorhandlingincleanarchitecture.domain.UserDataValidator
import kotlinx.coroutines.launch

// we can assume user data validator here as use case

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository,
) : ViewModel() {

    fun onRegisterClick(password: String) {
        when (val result = userDataValidator.validatePassword(password)) {
            is Result.Error -> {
                val error = result.error
                when(error){
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is Result.Success -> {

            }
        }


        viewModelScope.launch {

            when (val resut = repository.register(password)) {
                is Result.Error -> {
                    when (resut.error) {
                        DataError.Network.REQUEST_TIMEOUT -> TODO()
                        DataError.Network.TOO_MANY_REQUESTS -> TODO()
                        DataError.Network.NO_INTERNET -> TODO()
                        DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
                        DataError.Network.SERVER_ERROR -> TODO()
                        DataError.Network.SERIALIZATION -> TODO()
                        DataError.Network.UNKNOWN -> TODO()
                    }
                }

                is Result.Success -> {
                    resut.data
                }
            }
        }

    }


}