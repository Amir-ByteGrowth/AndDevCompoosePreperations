package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Notification
import android.app.Notification.MediaStyle
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.DEFAULT
import com.example.diffrentnotificationsjetpack.R

fun MediaNotification(context: Context) {

    val style = androidx.media.app.NotificationCompat.MediaStyle()
        .setShowActionsInCompactView(0,1,2)

    val nextIntent = Intent(context,NextPlay::class.java)
    val nextPendingIntent = PendingIntent.getBroadcast(context,3,nextIntent,PendingIntent.FLAG_MUTABLE)

    val previousIntent = Intent(context,PreviousPlay::class.java)
    val previousPendingIntent = PendingIntent.getBroadcast(context,4,previousIntent,PendingIntent.FLAG_MUTABLE)

    val playPauseIntent = Intent(context,PlayPause::class.java)
    val playPausePendingIntent = PendingIntent.getBroadcast(context,5,playPauseIntent,PendingIntent.FLAG_MUTABLE)




    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Media")
        .setContentText("Media Notification")
        .addAction(R.drawable.next,"Next",nextPendingIntent)
        .addAction(R.drawable.previous,"Previous",previousPendingIntent)
        .addAction(R.drawable.play_pause,"PlayPause",playPausePendingIntent)
        .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.amir))
        .setStyle(style)
        .build()


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(13, notification)
        } else {
            NotificationManagerCompat.from(context).notify(13, notification)
        }
    }

}

class NextPlay : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Next",Toast.LENGTH_SHORT).show()
    }

}

class PreviousPlay :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Previous",Toast.LENGTH_SHORT).show()
    }

}

class PlayPause :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Play/Pause",Toast.LENGTH_SHORT).show()
    }

}

