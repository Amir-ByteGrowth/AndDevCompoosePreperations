package com.example.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workmanager.ui.theme.CompoosePreperationsTheme
import com.example.workmanager.workers.MyCoroutineWorker
import com.example.workmanager.workers.SimpleWorker
import java.util.concurrent.TimeUnit

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestsCollection()
        setContent {
            CompoosePreperationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    private fun requestsCollection() {
//        simpleRequest()
//        coroutineWorker()
        periodicWorkRequest()
    }

    //    this request will for all from 26 to 34
    private fun simpleRequest() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<SimpleWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)
    }

    // now it is working on from 26 to 34 . if you want to show notification below 12 you must set is setexpedited and implement getforgoundinfo method els it will crash
    private fun coroutineWorker(){
        val simpleRequest =
            OneTimeWorkRequestBuilder<MyCoroutineWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)
    }

    private fun periodicWorkRequest() {
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<SimpleWorker>(16, TimeUnit.MINUTES).setInputData(
                workDataOf("input" to "periodicWith16Minutes")
            ).build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
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