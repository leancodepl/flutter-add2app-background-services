package co.leancode.add2appbackgroundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor

class ComputationForegroundService : Service() {
    companion object {
        private const val LOG_TAG = "ComputationService"
        private const val ENGINE_ID = "ComputationForegroundServiceEngine"
    }

    private val binder = LocalBinder()
    private val engine: FlutterEngine? = null
    // lateinit var api: Api.

    override fun onBind(p0: Intent?): IBinder? = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "Starting service")

            if (engine == null) {
                Log.d(LOG_TAG, "Initializing Flutter engine")
                startForeground(FLUTTER_)
            }

        return super.onStartCommand(intent, flags, startId)
    }

    fun stopService() {
        Log.d(LOG_TAG, "Stopping service")

        stopForeground(true)
        stopSelf()

        // FluttterCommon.destroyEngine(ENGINE_ID)
        engine = null
        Log.d(LOG_TAG, "Service stopped")
    }

    private fun startDartService() {
        val entrypoint = DartExecutor.DartEntrypoint("lib/main.dart", "backgroundServiceMain")

        // engine = FlutterCommon.createOrGetEngine(this, entrypoint, ENGINE_ID)

        engine!!.let {
            // Api.NativeCommonApi
        }
    }

    inner class LocalBinder : Binder() {
        fun getInstance(): ComputationForegroundService = this@ComputationForegroundService
    }
}