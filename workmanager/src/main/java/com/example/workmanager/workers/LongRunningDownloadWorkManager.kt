package com.example.workmanager.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.workmanager.R

class LongRunningDownloadWorkManager(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters) {
//working for all devices from 26 to 34
    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

    override suspend fun doWork(): Result {

        // Mark the Worker as important
        val progress = "Starting Download"

//            setForeground(createForegroundInfo(progress))
        // Set the Worker to run in the foreground
        setForeground(createForegroundInfo("amir"))

        download()
        return Result.success()
    }


    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo("Amir")
    }

    private fun download(inputUrl: String = "", outputFile: String = "") {
        // Downloads a file and updates bytes read
        // Calls setForeground() periodically when it needs to update
        // the ongoing Notification

        for (i in 0..10) {
            Thread.sleep(1000)
            println("Downloading task# $i")
        }

    }

    // Creates an instance of ForegroundInfo which can be used to update the
    // ongoing notification.
    private fun createForegroundInfo(progress: String): ForegroundInfo {
        val id = "abc"
        val title = "Download Worker"
        val cancel = "cancel download"
        // This PendingIntent can be used to cancel the worker
        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(getId())

        // Create a Notification channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        val notification = NotificationCompat.Builder(applicationContext, id)
            .setContentTitle(title)
            .setTicker(title)
            .setContentText(progress)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            // Add the cancel action to the notification which can
            // be used to cancel the worker
            .addAction(android.R.drawable.ic_delete, cancel, intent)
            .build()
        val foregroundInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(
                123,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            ForegroundInfo(123, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        }

        return foregroundInfo
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        // Create a Notification channel
        val channelName = "Expedited Work"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel("abc", channelName, importance)

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val KEY_INPUT_URL = "KEY_INPUT_URL"
        const val KEY_OUTPUT_FILE_NAME = "KEY_OUTPUT_FILE_NAME"
    }

}