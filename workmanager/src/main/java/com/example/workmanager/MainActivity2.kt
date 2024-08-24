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
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest.Companion.MIN_BACKOFF_MILLIS
import androidx.work.workDataOf
import com.example.workmanager.ui.theme.CompoosePreperationsTheme
import com.example.workmanager.workers.LongRunningDownloadWorkManager
import com.example.workmanager.workers.MyCoroutineWorker
import com.example.workmanager.workers.RetryAndBackoffPolicyWorker
import com.example.workmanager.workers.SimpleWorker
import com.example.workmanager.workers.UploadWork
import com.example.workmanager.workers.chainingworkers.FinalWorker
import com.example.workmanager.workers.chainingworkers.OutputWorker1
import com.example.workmanager.workers.chainingworkers.OutputWorker2
import com.example.workmanager.workers.chainingworkers.OutputWorker3
import com.example.workmanager.workers.chainingworkers.custominputmerger.CustomInputMerger
import com.example.workmanager.workers.chainingworkers.custominputmerger.FailureWork1
import com.example.workmanager.workers.chainingworkers.custominputmerger.FailureWork2
import com.example.workmanager.workers.chainingworkers.custominputmerger.FinalWorkerCustomManager
import com.example.workmanager.workers.chainingworkers.custominputmerger.Work1
import com.example.workmanager.workers.chainingworkers.custominputmerger.Work2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
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
//        periodicWorkRequest()
//        retryAndBackOfPolicyWorker()
//        addTagWorker()
//        enqueUniqueWorker()
//        getInfoAboutWorker()
//        simpleRequestCancel()
//        simpleRequestCancelWithCoroutineWorker()
//        workerChaining()
//        workerChainingWithCustomMerger()
//        failureWorkerChainingWithCustomMerger()
        longRunningDownloadCoroutineWorker()
//        uploadCoroutineWorker()
    }



    //    this request will for all from 26 to 34
    private fun simpleRequest() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<SimpleWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)
    }

    // on stop method // if we are doing long running task in dowork method they will continue running until we stop it mannualy
    private fun simpleRequestCancel() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<SimpleWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(simpleRequest.id)
            .observeForever {
                if (it.state == WorkInfo.State.RUNNING) {
                    WorkManager.getInstance(applicationContext).cancelWorkById(simpleRequest.id)
                }
            }

    }

    //onstop with coroutine worker it will cancel all tasks automatically
    private fun simpleRequestCancelWithCoroutineWorker() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<MyCoroutineWorker>().setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(simpleRequest.id)
            .observeForever {
                if (it.state == WorkInfo.State.RUNNING) {
                    CoroutineScope(Dispatchers.Default).launch {
                        try {
                            delay(10000)

                            WorkManager.getInstance(applicationContext).cancelWorkById(simpleRequest.id)
                        }catch (e:CancellationException){
                            println("Exception Caught")
                        }


                    }

                }
            }

    }

    // now it is working on from 26 to 34 . if you want to show notification below 12 you must set is setexpedited and implement getforgoundinfo method els it will crash
    private fun coroutineWorker() {
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

    private fun retryAndBackOfPolicyWorker() {
        val request =
            OneTimeWorkRequestBuilder<RetryAndBackoffPolicyWorker>().setInputData(
                workDataOf("input" to "RetryAndBackofPolicy")
            ).setBackoffCriteria(
                BackoffPolicy.LINEAR,
                MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            ).build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }

    private fun addTagWorker() {
        val request =
            OneTimeWorkRequestBuilder<RetryAndBackoffPolicyWorker>().setInputData(
                workDataOf("input" to "RetryAndBackofPolicy")
            ).setBackoffCriteria(
                BackoffPolicy.LINEAR,
                MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            ).addTag("one").build()
        WorkManager.getInstance(applicationContext).enqueue(request)

//        lifecycleScope.launch {
//            delay(5000)
//            println("Canceling Work")
//
//        }

//        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("one")
    }

    // cancel by name
    private fun enqueUniqueWorker() {
        val request =
            OneTimeWorkRequestBuilder<RetryAndBackoffPolicyWorker>().setInputData(
                workDataOf("input" to "RetryAndBackofPolicy")
            ).setBackoffCriteria(
                BackoffPolicy.LINEAR,
                MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            ).addTag("one").build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork("retryWork", ExistingWorkPolicy.KEEP, request)

    }

    private fun getInfoAboutWorker() {
        val request =
            OneTimeWorkRequestBuilder<RetryAndBackoffPolicyWorker>().setInputData(
                workDataOf("input" to "RetryAndBackofPolicy")
            ).setBackoffCriteria(
                BackoffPolicy.LINEAR,
                MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            ).build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork("retryWork", ExistingWorkPolicy.KEEP, request)

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(request.id)
            .observeForever { workInfo ->
                println("Work enqued Successfully  "+ workInfo?.state)
//                if (workInfo?.state == WorkInfo.State.ENQUEUED) {
//
//                }

            }


    }

    //worker  chaining parallel and then
    private fun workerChaining() {
        val parallelWorkRequest1 = OneTimeWorkRequestBuilder<OutputWorker1>()
            .addTag("parallel_work_tag")
            .build()

        val parallelWorkRequest2 = OneTimeWorkRequestBuilder<OutputWorker2>()
            .addTag("parallel_work_tag")
            .build()

        val parallelWorkRequest3 = OneTimeWorkRequestBuilder<OutputWorker3>()
            .addTag("parallel_work_tag")
            .build()
        val finalWorkRequest = OneTimeWorkRequestBuilder<FinalWorker>()
            .addTag("parallel_work_tag")
            .build()

        // if all parallel worker are passing data with same key will will use ArrayCreatingMerger so
        // we can get array of values with same key else system will overwrite all values to last
        // executing parent value

//        val finalWorkRequest = OneTimeWorkRequestBuilder<FinalWorker>().setInputMerger(
//            ArrayCreatingInputMerger::class.java)
//            .addTag("parallel_work_tag")
//            .build()

// Execute parallelWorkRequest1 and parallelWorkRequest2 in parallel
// and then execute finalWorkRequest
        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(parallelWorkRequest1, parallelWorkRequest2, parallelWorkRequest2,parallelWorkRequest3))
            .then(finalWorkRequest)
            .enqueue()

        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(finalWorkRequest.id)
            .observeForever {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    val output = it.outputData
                    println("Final Worker output   \n" + (output.getString("output") ?: " Empty or null data"))
                }
            }

    }

    //worker chaining with custom merger
    private fun workerChainingWithCustomMerger() {
        // Create the work requests for Work1 and Work2
        val work1 = OneTimeWorkRequestBuilder<Work1>()
            .setInputData(workDataOf("key1" to "value1_from_work1", "key2" to "value2_from_work1"))
            .build()

        val work2 = OneTimeWorkRequestBuilder<Work2>()
            .setInputData(workDataOf("key1" to "value1_from_work2", "key3" to "value3_from_work2"))
            .build()

// Create the final work request using the CustomInputMerger
        val finalWork = OneTimeWorkRequestBuilder<FinalWorkerCustomManager>()
            .setInputMerger(CustomInputMerger::class.java)
            .build()

// Enqueue Work1 and Work2 to run in parallel, followed by finalWork
        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(work1, work2))
            .then(finalWork)
            .enqueue()
    }

    //    failure in chain testin
    private fun failureWorkerChainingWithCustomMerger() {
        // Create the work requests for Work1 and Work2
        val work1 = OneTimeWorkRequestBuilder<FailureWork1>()
            .setInputData(workDataOf("key1" to "value1_from_work1", "key2" to "value2_from_work1"))
            .build()
// when this work fail all its dependent will fail
        val work2 = OneTimeWorkRequestBuilder<FailureWork2>()
            .setInputData(workDataOf("key1" to "value1_from_work2", "key3" to "value3_from_work2"))
            .build()

// Create the final work request using the CustomInputMerger
        val finalWork = OneTimeWorkRequestBuilder<FinalWorkerCustomManager>()
            .setInputMerger(CustomInputMerger::class.java)
            .build()

// Enqueue Work1 and Work2 to run in parallel, followed by finalWork
        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(work1, work2))
            .then(finalWork)
            .enqueue()
    }

    // long running download worker al hum du lilah working on all devices from 26 to 34
    private fun longRunningDownloadCoroutineWorker() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<LongRunningDownloadWorkManager>()
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)
    }

    // upload work request
    private fun uploadCoroutineWorker() {
        val simpleRequest =
            OneTimeWorkRequestBuilder<UploadWork>()
                .build()
        WorkManager.getInstance(applicationContext).enqueue(simpleRequest)
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