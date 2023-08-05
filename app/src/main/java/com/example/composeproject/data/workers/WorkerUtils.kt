package com.example.composeproject.data.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.composeproject.R

fun makeStatusNotification(message: String, context: Context) {
    val channelId = "VERBOSE_NOTIFICATION"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "VERBOSE_NOTIFICATION_CHANNEL_NAME"
        val description = "VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Work Request")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(longArrayOf(1000, 1000))

    NotificationManagerCompat.from(context).notify(1, builder.build())
}