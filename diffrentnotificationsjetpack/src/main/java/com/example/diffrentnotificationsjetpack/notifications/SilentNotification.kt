package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.DEFAULT
import com.example.diffrentnotificationsjetpack.R
import com.example.diffrentnotificationsjetpack.SILENT

/*
this notification is used to show the progress, or showing the foreground service may be
 */
fun SilentNotification(context: Context) {
    val notification = NotificationCompat.Builder(context, SILENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Silent")
        .setContentText("Silent Notification")
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(2, notification)
        } else {
            NotificationManagerCompat.from(context).notify(2, notification)
        }
    }
}