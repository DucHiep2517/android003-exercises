package com.example.myapplication.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("onStartCommand","======> $intent")

        createNotification()

        val notification = NotificationCompat.Builder(this,getString(R.string.notification_id))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("$this is running")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(
            123,
            notification
        )

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        Log.d("onStartCommand","======> onCreate")
        super.onCreate()
    }
}

fun Context.createNotification(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Create the NotificationChannel.
        val name = getString(R.string.notification_id)
        val descriptionText = getString(R.string.notification_des)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(getString(R.string.notification_id), name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}