package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.R
import com.example.diffrentnotificationsjetpack.URGENT

fun UrgentNotification(context: Context) {
    val notification = NotificationCompat.Builder(context, URGENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Urgent")
        .setContentText("Urgent Notification")
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(3, notification)
        } else {
            NotificationManagerCompat.from(context).notify(3, notification)
        }
    }
}