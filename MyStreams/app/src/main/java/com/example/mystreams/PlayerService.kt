package com.example.mystreams

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PlayerService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    fun playItem(srcUrl: String){

    }

    private fun fetchItem(srcUrl: String){

    }
}
