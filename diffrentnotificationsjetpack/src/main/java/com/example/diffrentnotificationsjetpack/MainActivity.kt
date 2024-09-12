package com.example.diffrentnotificationsjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diffrentnotificationsjetpack.notifications.ActionNotification
import com.example.diffrentnotificationsjetpack.notifications.BigPicuteNotification
import com.example.diffrentnotificationsjetpack.notifications.BigStyleNotification
import com.example.diffrentnotificationsjetpack.notifications.CustomNotification
import com.example.diffrentnotificationsjetpack.notifications.GroupNotification
import com.example.diffrentnotificationsjetpack.notifications.InBoxStyleNotification
import com.example.diffrentnotificationsjetpack.notifications.MediaNotification
import com.example.diffrentnotificationsjetpack.notifications.MessageStyleNotification
import com.example.diffrentnotificationsjetpack.notifications.SilentNotification
import com.example.diffrentnotificationsjetpack.notifications.SimpleNotification
import com.example.diffrentnotificationsjetpack.notifications.UrgentNotification
import com.example.diffrentnotificationsjetpack.notifications.createNotificationChannels
import com.example.diffrentnotificationsjetpack.notifications.showUpdateNotification
import com.example.diffrentnotificationsjetpack.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        createNotificationChannels(applicationContext)
//        showMessageNotification(applicationContext)
//        showUpdateNotification(applicationContext)
        setContent {
            CompoosePreperationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { SimpleNotification(this@MainActivity) }) {
                            Text(text = "Simple Notification")
                        }

                        Button(onClick = { SilentNotification(this@MainActivity) }) {
                            Text(text = "Silent Notification")
                        }

                        Button(onClick = { UrgentNotification(this@MainActivity) }) {
                            Text(text = "Urgent Notification")
                        }

                        Button(onClick = { GroupNotification(this@MainActivity) }) {
                            Text(text = "Group Notification")
                        }

                        Button(onClick = { ActionNotification(this@MainActivity) }) {
                            Text(text = "Action Notification")
                        }

                        Button(onClick = { BigStyleNotification(this@MainActivity) }) {
                            Text(text = "Big Text Style Notification")
                        }

                        Button(onClick = { BigPicuteNotification(this@MainActivity) }) {
                            Text(text = "Big Picture Style Notification")
                        }

                        Button(onClick = { InBoxStyleNotification(this@MainActivity) }) {
                            Text(text = "Inbox Style Notification")
                        }

                        Button(onClick = { MessageStyleNotification(this@MainActivity) }) {
                            Text(text = "Message Style Notification")
                        }

                        Button(onClick = { MediaNotification(this@MainActivity) }) {
                            Text(text = "Media Notification")
                        }

                        Button(onClick = { CustomNotification(this@MainActivity) }) {
                            Text(text = "Custom Notification")
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