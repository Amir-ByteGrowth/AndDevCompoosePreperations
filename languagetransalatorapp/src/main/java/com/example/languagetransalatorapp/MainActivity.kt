package com.example.languagetransalatorapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.languagetransalatorapp.service.TranslatorService
import com.example.languagetransalatorapp.ui.theme.CompoosePreperationsTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    // for audio translator
    private val speechRecognizer by lazy { SpeechRecognizer.createSpeechRecognizer(this) }

    //
    private var translatorService: TranslatorService? = null
    private var isBound = false

    // to bind our activity to service we have to create service connection
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            translatorService = (service as TranslatorService.TranslatorBinder).getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

    }


    @OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompoosePreperationsTheme {
                Surface(
                    modifier = Modifier
                        .safeContentPadding()
                        .fillMaxSize()
                ) {
                    // for getting permissions
                    val permission =
                        rememberPermissionState(permission = android.Manifest.permission.RECORD_AUDIO)
                    LaunchedEffect(key1 = Unit) {
                        permission.launchPermissionRequest()
                    }

                    var text by remember {
                        mutableStateOf("")
                    }
                    var translatedText by remember {
                        mutableStateOf("")
                    }

                    var isListen by remember {
                        mutableStateOf(false)
                    }

                    LaunchedEffect(isListen) {
                        if (isListen) {

                            listen(
                                onStartListen = { translatedText = "Start Listening" },
                                onResult = {
                                    text = it
                                    translatorService?.translate(
                                        text = it,
                                        onSuccess = {
                                            translatedText = it
                                            isListen = false
                                        },
                                        onFailure = {
                                            translatedText = "Error in recorded text"
                                            isListen = false
                                        })
                                })


                        }
                    }

                    Scaffold(topBar = { TopAppBar(title = { Text(text = "EnglishToUrdu") }) },
                        floatingActionButton =
                        {
                            Card(colors = CardDefaults.cardColors(containerColor = if (isListen) Color.Green else Color.Red)) {
                                IconButton(onClick = { isListen = !isListen }) {
                                    Icons.Default.Star
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            OutlinedTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(text = translatedText)
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = {
                                    translatorService?.translate(
                                        text,
                                        onSuccess = { translatedText = it },
                                        onFailure = { translatedText = "Error" })
                                }
                            ) {
                                Text(text = "Translate")
                            }
                        }
                    }


                }
            }
        }
    }


    fun listen(onStartListen: () -> Unit, onResult: (String) -> Unit) {
        val intent = Intent().apply {
            action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.current.language)
        }
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }

            override fun onEndOfSpeech() {
                speechRecognizer.stopListening()
            }

            override fun onError(error: Int) {

            }

            override fun onResults(results: Bundle?) {
                results?.let {
                    val result =
                        it.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.first()
                    result?.let(onResult)

                }
            }

            override fun onPartialResults(partialResults: Bundle?) {

            }

            override fun onEvent(eventType: Int, params: Bundle?) {

            }
        })

        speechRecognizer.startListening(intent)
    }


    // we will use these life cycle methods to connect with bound service
    override fun onStart() {
        super.onStart()
        val intent = Intent(this, TranslatorService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
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