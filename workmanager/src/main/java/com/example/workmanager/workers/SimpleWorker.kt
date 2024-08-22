package com.example.workmanager.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {


        try {

            val inputData = inputData.getString("input") ?: "empty"
            println("Input Data $inputData")

            for (i in 0..10){
                println("Task# $i m" )
                Thread.sleep(1000)

            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    override fun getForegroundInfo(): ForegroundInfo {
        val notificationId = 1
        val channelId = "expedited_work_channel"
        val notification = createNotification(channelId)
        return ForegroundInfo(notificationId, notification,
            FOREGROUND_SERVICE_TYPE_DATA_SYNC)
    }
    fun createNotification(channelId: String): Notification {
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
            .setContentText("Running expedited work... ")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .build()
    }



    override fun onStopped() {
        println("Worker was stopped")
        super.onStopped()
    }

}


