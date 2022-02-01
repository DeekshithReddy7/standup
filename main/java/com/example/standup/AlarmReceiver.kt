package com.example.standup

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import androidx.core.app.NotificationCompat

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT


class AlarmReceiver: BroadcastReceiver() {
    private var mNotificationManager: NotificationManager? = null

    // Notification ID.
    private val NOTIFICATION_ID = 0

    // Notification channel ID.
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    override fun onReceive(p0: Context?, p1: Intent?) {
        mNotificationManager = p0?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Deliver the notification.
        deliverNotification(p0)
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun deliverNotification(context: Context) {
        // Create the content intent for the notification, which launches
        // this activity
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(context,
            NOTIFICATION_ID,
            contentIntent,
            FLAG_UPDATE_CURRENT)
        // Build the notification
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)

        // Deliver the notification
        mNotificationManager!!.notify(NOTIFICATION_ID, builder.build())
    }
}