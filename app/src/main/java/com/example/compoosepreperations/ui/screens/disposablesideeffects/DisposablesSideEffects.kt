package com.example.compoosepreperations.ui.screens.disposablesideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun DisposableSideEffectHomeScreen(
    startEvent: () -> Unit,
    stopEvent: () -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {

    val currentOnStart by rememberUpdatedState(startEvent)
    val currentOnStop by rememberUpdatedState(stopEvent)

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart.invoke()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop.invoke()
            }

        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


}