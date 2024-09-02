package com.example.globalsnackbar.globalsnakbar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


data class SnackBarEvent(
    val message: String,
    val snackBarAction: SnackBarAction? = null,

    )


data class SnackBarAction(
    val name: String,
    val action: suspend () -> Unit,
)

object SnackBarController {

    // here channel is behaving just like shared flow but the difference is, this is meant only for one subscriber or observer
    private val _events = Channel<SnackBarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent) {
        _events.send(event)
    }

}