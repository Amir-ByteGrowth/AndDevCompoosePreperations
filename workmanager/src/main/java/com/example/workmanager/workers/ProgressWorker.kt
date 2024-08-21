package com.example.workmanager.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class ProgressWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val notificationId = 1
    private val channelId = "progress_channel"

    override fun doWork(): Result {
        // Create the notification channel
        createNotificationChannel()

        // Start work and update progress
        for (progress in 0..100 step 10) {
            // Simulate work
            Thread.sleep(500)

            // Update progress in the notification
            showProgressNotification(progress)

            // Report progress to the WorkManager
            setProgressAsync(workDataOf("PROGRESS" to progress))
        }
        Thread.sleep(500)
        cancelNotification()
        // Indicate work is finished
//        showProgressNotification(100)

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Progress"
            val descriptionText = "Shows progress of the work"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showProgressNotification(progress: Int) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Work in progress")
            .setContentText("Progress: $progress%")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setProgress(100, progress, false)
            .setOngoing(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    fun cancelNotification(){
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(notificationId)

    }

}