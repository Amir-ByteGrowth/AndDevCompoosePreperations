package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.PendingIntentCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.R
import com.example.diffrentnotificationsjetpack.SILENT

fun ActionNotification(context: Context) {

    val intent = Intent(context,ReplyBroadCasReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context,3,intent,PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(context, SILENT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Action")
        .setContentText("Action Notification")
        .addAction(R.drawable.ic_launcher_background,"reply",pendingIntent)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(8, notification)
        } else {
            NotificationManagerCompat.from(context).notify(8, notification)
        }
    }

}

class ReplyBroadCasReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Reply",Toast.LENGTH_SHORT).show()
    }
}