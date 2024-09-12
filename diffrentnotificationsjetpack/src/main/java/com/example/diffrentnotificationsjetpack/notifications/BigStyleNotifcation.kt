package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.R
import com.example.diffrentnotificationsjetpack.SILENT

fun BigStyleNotification(context: Context) {

    val style = NotificationCompat.BigTextStyle()
        .setSummaryText("Summary")
        .setBigContentTitle("Big Title")
        .bigText(",Here is very big text,Here is very big text,Here is very big text,Here is very big text,Here is very big text,Here is very big text")


    val notification = NotificationCompat.Builder(context, SILENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("BigText")
        .setContentText("Big Text Notification")
        .setStyle(style)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(9, notification)
        } else {
            NotificationManagerCompat.from(context).notify(2, notification)
        }
    }
}


fun BigPicuteNotification(context: Context) {

    val style = NotificationCompat.BigPictureStyle()
        .setSummaryText("Summary")
        .setBigContentTitle("Big Title")
        .bigPicture(BitmapFactory.decodeResource(context.resources,R.drawable.amir))

    val notification = NotificationCompat.Builder(context, SILENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("BigPicture")
        .setContentText("Big Picture Notification")
        .setStyle(style)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(10, notification)
        } else {
            NotificationManagerCompat.from(context).notify(10, notification)
        }
    }
}


fun InBoxStyleNotification(context: Context) {

    val style = NotificationCompat.InboxStyle()
        .setSummaryText("Summary")
        .setBigContentTitle("Inbox Big Title")
        .addLine("First Line")
        .addLine("Second Line")

    val notification = NotificationCompat.Builder(context, SILENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("InBoxStyle")
        .setContentText("Inbox Style Notification")
        .setStyle(style)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(11, notification)
        } else {
            NotificationManagerCompat.from(context).notify(11, notification)
        }
    }
}