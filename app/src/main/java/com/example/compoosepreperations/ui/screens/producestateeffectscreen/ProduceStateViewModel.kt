package com.example.compoosepreperations.ui.screens.producestateeffectscreen

import androidx.lifecycle.ViewModel
import kotlin.Result

class ProduceStateViewModel :ViewModel() {



}

sealed interface Response{
    object Loading : Response
    data class Successful(val user: User,) : Response
    data class Error(val exception:Throwable) : Response
}
data class User(val name:String,val detail:String,)