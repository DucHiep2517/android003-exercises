package com.example.myapplication.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast

class NetworkStatusReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
       context?.let {
           val cm = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
           val networkInfo = cm.activeNetworkInfo

           val isConnected = networkInfo?.isConnectedOrConnecting == true

           if (isConnected) {
               Toast.makeText(it, "Network is connected", Toast.LENGTH_SHORT).show()
               Log.d("NetworkStatusReceiver", "Network is connected")
           } else {
               Toast.makeText(it, "Network is not connected", Toast.LENGTH_SHORT).show()
               Log.d("NetworkStatusReceiver", "Network is not connected")
           }
       }
    }

}
