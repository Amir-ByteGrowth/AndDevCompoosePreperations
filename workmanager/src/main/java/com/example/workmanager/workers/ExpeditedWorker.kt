package com.example.workmanager.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters

class ExpeditedWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Perform your work here
        for (i in 0..10){
            Thread.sleep(2000)
            println("task# $i")

        }
        return Result.success()
    }

    override fun getForegroundInfo(): ForegroundInfo {
        val notificationId = 1
        val channelId = "expedited_work_channel"
        val notification = createNotification(channelId)
        return ForegroundInfo(notificationId, notification)
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
            .setContentText("Running expedited work...")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }
}