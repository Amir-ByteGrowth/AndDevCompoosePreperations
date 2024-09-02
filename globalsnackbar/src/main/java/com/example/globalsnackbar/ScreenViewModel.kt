package com.example.globalsnackbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalsnackbar.globalsnakbar.SnackBarAction
import com.example.globalsnackbar.globalsnakbar.SnackBarController
import com.example.globalsnackbar.globalsnakbar.SnackBarEvent
import kotlinx.coroutines.launch

class ScreenViewModel :ViewModel() {

    fun showSnackBar(){
        viewModelScope.launch {
            SnackBarController.sendEvent(event = SnackBarEvent(message = "Hellow! from Viewmodel", snackBarAction = SnackBarAction(name = "Click Me", action = {
                SnackBarController.sendEvent(event = SnackBarEvent(message = "Action! Processed"))
            })))
        }
    }
}