package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.ui.util.trace
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.DEFAULT
import com.example.diffrentnotificationsjetpack.R

const val GROUP_NAME ="group1"

fun GroupNotification(context: Context) {
    val notificationA = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("GN1")
        .setContentText("Group Notification 1")
        .setGroup(GROUP_NAME)
        .build()

    val notificationB = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("GN2")
        .setContentText("Group Notification 2")
        .setGroup(GROUP_NAME)
        .build()

    val groupBuilder = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Group")
        .setContentText("This is group of notifications")
        .setGroup(GROUP_NAME)
        .setGroupSummary(true)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).apply {
                notify(4,notificationA)
                notify(5,notificationB)
                notify(6,groupBuilder)
            }
        } else {
            NotificationManagerCompat.from(context).apply {
                notify(4,notificationA)
                notify(5,notificationB)
                notify(6,groupBuilder)
            }
        }
    }
}