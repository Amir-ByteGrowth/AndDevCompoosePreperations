package com.example.compoosepreperations.ui.screens.producestateeffectscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun UserProduceStateEffect(modifier: Modifier = Modifier) {
    val uiState by ReturnUserUiState()
    Log.d("UserProduceStateEffect","Called")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val data = uiState) {
            Response.Loading -> {
                Text(text = "Data is Loading")
            }

            is Response.Error -> {
                Text(text = "An error occurred")
            }

            is Response.Successful -> {
                Text(text = data.user.name)
                Text(text = data.user.detail)
            }
        }
    }
}

@Composable
fun ReturnUserUiState(modifier: Modifier = Modifier): State<Response> {
    return produceState<Response>(initialValue = Response.Loading) {
        try {
            delay(3000)
            value = Response.Successful(User("Amir", "Amir is android applicaton developer"))
        } catch (e: Exception) {
            value = Response.Error(e)
        }
    }
}