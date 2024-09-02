package com.example.globalsnackbar.onetimeevent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OneTimeEventViewModel : ViewModel() {
// best solution for one time event and we have to use Dispatchers.Main.immediate to direct processor to this job on high priority
    private val navigationChannel = Channel<NavigationEvent>()
    val navigationChannelFlow = navigationChannel.receiveAsFlow()


    //    private val _navigationSharedFlow = MutableSharedFlow<NavigationEvent>()
    // to add buffer
    private val _navigationSharedFlow = MutableSharedFlow<NavigationEvent>(replay = 1)
    val navigationSharedFlow = _navigationSharedFlow.asSharedFlow()

    // second solution to one time event
    var isLoggedIn = MutableStateFlow(false)
        private set

    var state by mutableStateOf(LoginState())
        private set





    fun login() {

        viewModelScope.launch {
            state = state.copy(isLoading = true)

            delay(5000L)
// if there is no listner or reciever to this channel then corroutine will be suspended until reciever is available
            // channel one time event best one
//            navigationChannel.send(NavigationEvent.NavigateToProfile)

            // sharedflow
            // if the activity go to background screen and stay there for long time sharedflow got get expire. while channel flow wait for at least one time collecting
            // we can set buffer by adding replay in shared flow
//            _navigationSharedFlow.emit(NavigationEvent.NavigateToProfile)

//            state = state.copy(isLoading = false)

            ///////////////
            //managing state using mutable state
            // if we do this we have to mannualy set isloggin false because if we come back from profile
            // and its value is true then it will trigger again toward profile
            state = state.copy(isLoading = false, isLoggedIn = true)
        }
    }
//now it will work fine  while navigating back
    fun onNavigateBack(){
        state = state.copy(isLoggedIn = false)
    }

}


sealed interface NavigationEvent {
    object NavigateToProfile : NavigationEvent
}

data class LoginState(val isLoading: Boolean = false, val isLoggedIn: Boolean = false)