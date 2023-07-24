package com.example.core

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder

private const val CHANNEL_ID = "14"

class MyCoreBroadcastReceiver() :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            Log.e("Maksim", "Broadcast is started")
            if (intent?.action.equals("ACTION_SNOOZE")) {
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.cancelAll()

                val newIntent = Intent(context, MyCoreBroadcastReceiver::class.java)
                intent?.action = "REMINDER_ACTION"
                val pendingIntentForBroadcast = PendingIntent.getBroadcast(
                    context,
                    0,
                    newIntent,
                    PendingIntent.FLAG_IMMUTABLE,
                )

                val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
                AlarmManagerCompat.setExactAndAllowWhileIdle(
                    alarmManager,
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() +
                        10000,
                    pendingIntentForBroadcast,
                )
            } else {
                createNotification(context)
            }
        }
    }

    private fun createNotification(context: Context) {
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.detailNewsFragment)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.calendar_icon)
            .setContentTitle("titleNotification")
            .setContentText("Напоминаем, что мы будем очень признательны, если вы сможете пожертвовать еще больше.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(0, builder)
        }
    }
}
