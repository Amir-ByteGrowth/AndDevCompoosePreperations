package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.diffrentnotificationsjetpack.MainActivity
import com.example.diffrentnotificationsjetpack.R

// Function to create the notification channels
fun createNotificationChannels(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel1 = NotificationChannel(
            "channel1_id",
            "Messages",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This channel is used for message notifications"
        }

        val channel2 = NotificationChannel(
            "channel2_id",
            "Updates",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "This channel is used for update notifications"
        }

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel1)
        notificationManager?.createNotificationChannel(channel2)
    }
}

// Function to show a notification for the Messages channel
fun showMessageNotification(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, "channel1_id")
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("New Message")
        .setContentText("You have received a new message.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
        notify(1, builder.build())
    }
}

// Function to show a notification for the Updates channel
fun showUpdateNotification(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, "channel2_id")
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("App Update")
        .setContentText("A new update is available.")
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        notify(2, builder.build())
    }
}