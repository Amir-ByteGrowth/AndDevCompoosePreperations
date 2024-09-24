package com.example.languagetransalatorapp

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.languagetransalatorapp.service.BackgroundLocationTrackingService
import com.example.languagetransalatorapp.ui.theme.CompoosePreperationsTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class StartLocationServiceActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompoosePreperationsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val permissions = rememberMultiplePermissionsState(
                        permissions =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            listOf(
                                Manifest.permission.POST_NOTIFICATIONS,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        } else {
                            listOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        }


                    )

                    LaunchedEffect(Unit) {
                        permissions.launchMultiplePermissionRequest()
                    }

                    when {
                        permissions.allPermissionsGranted -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Button(onClick = {

                                    val intent = Intent(
                                        this@StartLocationServiceActivity,
                                        BackgroundLocationTrackingService::class.java
                                    )
                                    startService(intent)

                                }) {
                                    Text(text = "StartService")
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Button(onClick = {

                                    val intent = Intent(
                                        this@StartLocationServiceActivity,
                                        BackgroundLocationTrackingService::class.java
                                    )
                                    stopService(intent)

                                }) {
                                    Text(text = "StopService")
                                }
                            }
                        }

                        permissions.shouldShowRationale -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(onClick = { permissions.launchMultiplePermissionRequest() }) {
                                    Text(text = "Give Required permissions")
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CompoosePreperationsTheme {
        Greeting2("Android")
    }
}