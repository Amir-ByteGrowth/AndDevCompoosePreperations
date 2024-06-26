package com.example.compoosepreperations.ui.screens.rememberupdatedstateeffect

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun MainCompo(modifier: Modifier = Modifier) {
    Log.d("RememberUpdate", "MainCompo")
    var showLandingScreen by remember {
        mutableStateOf(true)
    }

    if (showLandingScreen) {
        LandingScreen {
            showLandingScreen = false
        }
    } else {
        MainScreen()
    }

}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "Main Screen")
    }
    Log.d("RememberUpdate", "MainScreen")
}

@Composable
fun LandingScreen(callBack: () -> Unit) {
    val closLandingScreen by rememberUpdatedState(callBack)
    LaunchedEffect(Unit) {
        delay(3000)
        closLandingScreen.invoke()
    }
    Box{
        Text(text = "Landing Screen")
    }
    Log.d("RememberUpdate", "LandingScreen")
}