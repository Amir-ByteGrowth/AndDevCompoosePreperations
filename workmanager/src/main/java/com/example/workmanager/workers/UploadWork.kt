package com.example.workmanager.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.ServiceInfo
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.Q
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.workmanager.MainActivity
import com.example.workmanager.R
import kotlinx.coroutines.delay

class UploadWork(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    //CoroutineWorker
    companion object {
        const val keywork = "keywork"
        const val NOTIFICATION_NAME = "appid"
        const val NOTIFICATION_CHANNEL = "appid_channel_505"
    }

    override suspend fun doWork(): Result {

        setForeground(createForegroundInfo())
        //write your code network call or long runnung work

        for (i in 0..10){
            println("task # $i")
            delay(1000)
        }

        return Result.success()

    }

    private fun createForegroundInfo(): ForegroundInfo {
        return if (SDK_INT >= Q) {
            ForegroundInfo(15, sendNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(15, sendNotification())
        }
    }

    private fun sendNotification(): Notification {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent = getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Android 14 service test")
            .setContentText("SDK14")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        if (SDK_INT >= O) {
            notification.setChannelId(NOTIFICATION_CHANNEL)
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(101, notification.build())
        return notification.build()
    }

}