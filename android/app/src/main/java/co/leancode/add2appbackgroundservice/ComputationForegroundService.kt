package co.leancode.add2appbackgroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ComputationForegroundService : Service() {
    companion object {
        private const val LOG_TAG = "ComputationForegroundService"
        private const val ENGINE_ID = "ComputationForegroundServiceEngine"
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}