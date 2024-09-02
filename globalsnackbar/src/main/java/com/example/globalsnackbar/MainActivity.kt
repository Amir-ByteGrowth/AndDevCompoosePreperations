@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.globalsnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.globalsnackbar.globalsnakbar.ObserveAsEvents
import com.example.globalsnackbar.globalsnakbar.SnackBarController
import com.example.globalsnackbar.ui.theme.CompoosePreperationsTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CompoosePreperationsTheme {

                val snackBarHostState = remember {
                    SnackbarHostState()
                }
                val navController = rememberNavController()

                val scope = rememberCoroutineScope()
                ObserveAsEvents(flow = SnackBarController.events, snackBarHostState) { event ->
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss()
                        val result = snackBarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.snackBarAction?.name,
                            duration = SnackbarDuration.Long
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            event.snackBarAction?.action?.invoke()
                        }
                    }
                }


                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "screenA"
                    ) {
                        composable("screenA") {
                            ScreenA {
                                navController.navigate("screenB")
                            }
                        }

                        composable("screenB") {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Screen B")
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}