package com.example.workmanager.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MyCoroutineWorker(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters) {

        var timePassed=0

    override suspend fun doWork(): Result {

        return try {
            delay(5000)
//            setForeground(ForegroundInfo(1,createNotification("no_1")))
            // Perform the task in a coroutine
            for (i in 0..10){
                timePassed=i
                delay(1000)
                println("task# $i")

            }
            val data = fetchDataFromNetwork()
            println("NetworkRequest $data")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private suspend fun fetchDataFromNetwork(): String {
        // Simulating a network call
        delay(1000)
        return "Data Fetched"
    }

    private fun createNotification(channelId: String): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Expedited Work"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Expedited Work")
            .setContentText("Running expedited work... $timePassed")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {

        val notificationId = 1
        val channelId = "expedited_work_channel"
        val notification = createNotification(channelId)
        return ForegroundInfo(notificationId, notification)
    }

}