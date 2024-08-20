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
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.WorkerParameters
import androidx.work.impl.WorkManagerImpl
import com.example.workmanager.ui.theme.CompoosePreperationsTheme
import com.example.workmanager.workers.ExpeditedWorker
import com.example.workmanager.workers.WorkManagerPractice
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
            .setRequiresCharging(true)
            .build()

//        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkManagerPractice>().setConstraints(constraints).setInitialDelay(duration = Duration.ofMillis(5000)).build()
        // for expedited work request we can not use initial delay, charging  constraint or metered network
//        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkManagerPractice>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()
//     val sometheing=   WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest).result
//    println(sometheing.toString())

        uniquieWorkInQueu()

        setContent {
            CompoosePreperationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


  // for unique work , work should be unique in query
  fun uniquieWorkInQueu(){
//      this will not work for expedited work because we did not implemented forground service info method
//      val uploadWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<WorkManagerPractice>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()

      //this will work and it is showing notification if mobile is below 12
      val uploadWorkRequest: OneTimeWorkRequest = OneTimeWorkRequestBuilder<ExpeditedWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST).build()


      WorkManager.getInstance(applicationContext).enqueueUniqueWork(
          "sendLogs",
          ExistingWorkPolicy.KEEP,
          uploadWorkRequest
      )
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