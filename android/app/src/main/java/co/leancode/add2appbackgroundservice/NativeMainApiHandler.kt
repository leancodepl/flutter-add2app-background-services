package co.leancode.add2appbackgroundservice

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import co.leancode.add2appbackgroundservice.pigeon.Api

class NativeMainApiHandler(private val context: Context): Api.NativeMainApi {
    companion object {
        private const val LOG_TAG = "NativeMainApiHandler"
    }

    private var computationService: ComputationForegroundService? = null
    private var serviceConnection: ServiceConnection? = null

    override fun startService(notification: Api.ComputationNotification) {
        Log.d(LOG_TAG, "Starting computation service")

        val serviceIntent = Intent(context, ComputationForegroundService::class.java)
        ComputationServiceNotification.appendNotificationToIntent(serviceIntent, notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }

        serviceConnection = createServiceConnection()
        context.bindService(serviceIntent, serviceConnection!!, Context.BIND_AUTO_CREATE)

        Log.d(LOG_TAG, "Computation service started")
    }

    override fun stopService() {
        Log.d(LOG_TAG, "Stopping computation service")

        computationService?.stopService()
        computationService = null
        serviceConnection?.let {
            context.unbindService(it)
            serviceConnection = null
        }

        Log.d(LOG_TAG, "Computation service stopped")
    }

    fun onDestroy() {
        serviceConnection?.let {
            context.unbindService(it)
        }
    }

    private fun createServiceConnection(): ServiceConnection {
        return object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                computationService = (service as ComputationForegroundService.LocalBinder).getInstance()
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                computationService = null
            }
        }
    }

}