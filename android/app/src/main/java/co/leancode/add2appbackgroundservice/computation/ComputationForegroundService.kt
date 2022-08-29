package co.leancode.add2appbackgroundservice.computation

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import co.leancode.add2appbackgroundservice.AppFlutterEngine
import co.leancode.add2appbackgroundservice.FlutterUtils
import co.leancode.add2appbackgroundservice.pigeon.Api
import io.flutter.embedding.engine.FlutterEngine

class ComputationForegroundService : Service() {
    companion object {
        private const val LOG_TAG = "ComputationService"
        private const val SERVICE_ID = 1
    }

    private val binder = LocalBinder()
    private var engine: FlutterEngine? = null
    lateinit var api: Api.FlutterMainApi

    override fun onBind(p0: Intent?): IBinder? = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "Starting service")

        if (engine == null) {
            Log.d(LOG_TAG, "Initializing Flutter engine")
            ComputationServiceNotification.createNotificationChannel(applicationContext)
            val notification =
                ComputationServiceNotification.createNotification(applicationContext, intent!!)
            startForeground(SERVICE_ID, notification)
            startDartService()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun stopService() {
        Log.d(LOG_TAG, "Stopping service")

        stopForeground(true)
        stopSelf()
        FlutterUtils.destroyEngine(AppFlutterEngine.computationService)
        engine = null

        Log.d(LOG_TAG, "Service stopped")
    }

    private fun startDartService() {
        engine = FlutterUtils.createOrGetEngine(this, AppFlutterEngine.computationService)

        engine!!.let {
            Api.NativeBackgroundServiceApi.setup(it.dartExecutor, ComputationServiceApiHandler(this))
            api = Api.FlutterMainApi(it.dartExecutor)
        }
    }

    inner class LocalBinder : Binder() {
        fun getInstance(): ComputationForegroundService = this@ComputationForegroundService
    }
}