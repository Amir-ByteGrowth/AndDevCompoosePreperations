package com.example.globalsnackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.globalsnackbar.globalsnakbar.SnackBarController.sendEvent
import com.example.globalsnackbar.globalsnakbar.SnackBarEvent
import kotlinx.coroutines.launch


@Composable
fun ScreenA(modifier: Modifier = Modifier, screenViewModel: ScreenViewModel = viewModel(),onNavigate:() ->Unit) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            scope.launch {
                sendEvent(event = SnackBarEvent(message = "Hello World"))
            }


        }) {
            Text(text = "Show Snack Bar")
        }


        Button(onClick = {
            screenViewModel.showSnackBar()
        }) {
            Text(text = "Show snackbar with action")
        }

        Button(onClick = onNavigate) {
            Text(text = "ScreenB")
        }

    }
}