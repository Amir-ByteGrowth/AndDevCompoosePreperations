package com.example.workmanager.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.SettableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    ListenableWorker(appContext, workerParams) {

    override fun getForegroundInfoAsync(): ListenableFuture<ForegroundInfo> {
        val future = SettableFuture.create<ForegroundInfo>()
        CoroutineScope(Dispatchers.IO).launch {
            // Perform a background operation, e.g., fetching data from a database
            val data = fetchDataFromDatabase()
            delay(3000)
            // Now construct the ForegroundInfo based on the fetched data

            val notificationId = 1
            val channelId = "expedited_work_channel"
            val notification = createNotification(channelId, data)

            val foregroundInfo = ForegroundInfo(
                1,
                notification
            )

            // Set the result in the future
            future.set(foregroundInfo)
        }
        return future
    }

    override fun startWork(): ListenableFuture<Result> {
        val future = SettableFuture.create<Result>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Perform your work here
//                val result = doWork()
                println("StartWork Executed")
                // Return success or failure
                future.set(Result.success())
            } catch (e: Exception) {
                // Handle any exceptions
                future.set(Result.failure())
            }
        }

        return future
    }


    private suspend fun fetchDataFromDatabase(): String {
        // Simulate a database fetch operation
        return withContext(Dispatchers.IO) {
            // Fetch your data here
            "Sample Data"
        }
    }


    private fun createNotification(channelId: String, data: String): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Expedited Work"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Expedited Foreground Work")
            .setContentText(data)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }
}