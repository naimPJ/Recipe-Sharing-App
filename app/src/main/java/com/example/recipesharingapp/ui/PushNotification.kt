package com.example.recipesharingapp.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.recipesharingapp.MainActivity

@RequiresApi(Build.VERSION_CODES.O)
fun sendNotification(title: String, notificationBodyText: String, count: Int, context: Context) {

    val intent = Intent(
        context,
        MainActivity::class.java
    )


    val requestCode = System.currentTimeMillis().toInt()


    val pendingIntent = PendingIntent.getActivity(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )


    intent.putExtra("Notifications", count.toString())


    val channelId = "RecipeSharingApp"
    val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)


    val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(notificationBodyText)
        )
        .setShowWhen(true)
        .setWhen(System.currentTimeMillis())
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent)


    val manager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    val channel = NotificationChannel(
        channelId,
        "RecipeSharingApp",
        NotificationManager.IMPORTANCE_HIGH
    )


    channel.setShowBadge(true)
    channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
    manager.createNotificationChannel(channel)


    val notificationId = System.currentTimeMillis().toInt()
    manager.notify(notificationId, builder.build())
}