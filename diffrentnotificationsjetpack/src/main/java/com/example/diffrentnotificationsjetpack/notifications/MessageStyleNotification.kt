package com.example.diffrentnotificationsjetpack.notifications

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import com.example.diffrentnotificationsjetpack.DEFAULT
import com.example.diffrentnotificationsjetpack.R


const val REPLY = "reply"

fun MessageStyleNotification(context: Context) {

    val replyInput = RemoteInput.Builder(REPLY).build()
    val intent = Intent(context, MessageReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        1,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )

    val action = NotificationCompat.Action.Builder(R.drawable.reply, "Reply", pendingIntent)
        .addRemoteInput(replyInput).build()


    val person = Person.Builder().setName("Amir").build()
    val messageStyle = NotificationCompat.MessagingStyle(person)
        .setConversationTitle("Conversation Title")
        .addMessage("Message 1", System.currentTimeMillis(), person)
        .addMessage("Message 2", System.currentTimeMillis(), person)

    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Message")
        .setContentText("Message Notification")
        .setStyle(messageStyle)
        .addAction(action)
        .build()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                context,
                POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(12, notification)
        } else {
            NotificationManagerCompat.from(context).notify(12, notification)
        }
    }
}


class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        RemoteInput.getResultsFromIntent(intent)?.getCharSequence(REPLY).let { text ->


            val replyInput = RemoteInput.Builder(REPLY).build()
            val replyIntent = Intent(context, MessageReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                replyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val action = NotificationCompat.Action.Builder(R.drawable.reply, "Reply", pendingIntent)
                .addRemoteInput(replyInput).build()


            val person = Person.Builder().setName("Amir").build()
            val person2 = Person.Builder().setName("Rashid").build()

            val messageStyle = NotificationCompat.MessagingStyle(person)
                .setConversationTitle("Conversation Title")
                .addMessage("Message 1", System.currentTimeMillis(), person)
                .addMessage("Message 2", System.currentTimeMillis(), person)
                .addMessage(text, System.currentTimeMillis(), person2)

            val notification = NotificationCompat.Builder(context, DEFAULT)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Message")
                .setContentText("Message Notification")
                .setStyle(messageStyle)
                .addAction(action)
                .build()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    NotificationManagerCompat.from(context).notify(12, notification)
                } else {
                    NotificationManagerCompat.from(context).notify(12, notification)
                }
            }


        }
    }

}