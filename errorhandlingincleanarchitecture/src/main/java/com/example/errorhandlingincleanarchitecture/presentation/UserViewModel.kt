package com.example.errorhandlingincleanarchitecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.errorhandlingincleanarchitecture.domain.AuthRepository
import com.example.errorhandlingincleanarchitecture.domain.Result
import com.example.errorhandlingincleanarchitecture.domain.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

// we can assume user data validator here as use case

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository,
) : ViewModel() {

    private val eventChannel = Channel<UserEvent>()
    val events=eventChannel.receiveAsFlow()


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
//                    when (resut.error) {
//                        DataError.Network.REQUEST_TIMEOUT -> TODO()
//                        DataError.Network.TOO_MANY_REQUESTS -> TODO()
//                        DataError.Network.NO_INTERNET -> TODO()
//                        DataError.Network.PAYLOAD_TOO_LARGE -> TODO()
//                        DataError.Network.SERVER_ERROR -> TODO()
//                        DataError.Network.SERIALIZATION -> TODO()
//                        DataError.Network.UNKNOWN -> TODO()

//                    }

                    //      we have implemented error parsing in asUiTextFile wo we will will do this as
                    val errorMessage = resut.error.asUiText()
                    eventChannel.send(UserEvent.Error(errorMessage))
                }

                is Result.Success -> {
                    resut.data
                }
            }
        }

    }


}

sealed interface UserEvent {
    data class Error(val error: UiText) : UserEvent
}