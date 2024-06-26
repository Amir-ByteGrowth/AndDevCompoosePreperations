package com.example.compoosepreperations.ui.screens.launcheffects

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun LaunchEffectsScreen() {
    var showSnackBar by remember { mutableStateOf(false) }
    SnackBarContent(showSnackBar = showSnackBar) {
        showSnackBar = !showSnackBar
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackBarContent(
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    showSnackBar: Boolean,
    onClick: () -> Unit,

    ) {
    LaunchedEffect(showSnackBar) {
        Log.d("SnackBarContent", "LaunchedEffect")
        snackBarHostState.showSnackbar(message = "Message", duration = SnackbarDuration.Short)
    }


    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }, topBar = {
        TopAppBar(
            title = { Text(text = "SnackBar Example") }, actions = {
                Button(onClick = { onClick.invoke() }) {
                    Text(text = "Show/Hide Snack")
                }
            })
    }) { innerPAdding ->
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(innerPAdding)) {
            Text(text = "Inside")
        }
        Log.d("SnackBarContent", "Comp")
    }
}