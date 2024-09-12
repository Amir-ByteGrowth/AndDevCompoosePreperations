package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.DEFAULT
import com.example.diffrentnotificationsjetpack.R

fun CustomNotification(context: Context) {

    val smallCustomView = RemoteViews(context.packageName, R.layout.small_custom_layout)
    val customView = RemoteViews(context.packageName, R.layout.custom_layout)

    val intent = Intent(context,CustomNotificationReceiver::class.java)
    val customNotificationPendingIntent = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_MUTABLE)
     customView.setOnClickPendingIntent(R.id.btnAction,customNotificationPendingIntent)

    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setCustomContentView(smallCustomView)
        .setCustomBigContentView(customView)
        .build()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(15, notification)
        } else {
            NotificationManagerCompat.from(context).notify(15, notification)
        }
    }
}


class CustomNotificationReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
       Toast.makeText(context,"Custom Notification",Toast.LENGTH_SHORT).show()
    }

}