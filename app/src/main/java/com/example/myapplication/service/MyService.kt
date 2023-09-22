package com.example.myapplication.service

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class MyService : Service() {
    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
        const val FOREGROUND_SERVICE_ID = 111
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        Log.d(CHANNEL_ID,"======> createNotificationChannel")
        createNotificationChannel()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(CHANNEL_ID,"======> $intent")

        val notification = createNotification()

        startForeground(FOREGROUND_SERVICE_ID, notification)
        Log.d(CHANNEL_ID,"======> complete")
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            val name = getString(R.string.notification_name)
            val descriptionText = getString(R.string.notification_des)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {

        return NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("$this is running")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }
}