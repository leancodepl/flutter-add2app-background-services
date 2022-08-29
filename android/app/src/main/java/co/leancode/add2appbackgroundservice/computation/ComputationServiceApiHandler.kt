package co.leancode.add2appbackgroundservice.computation

import android.content.Intent
import co.leancode.add2appbackgroundservice.dialog.FlutterDialogActivity
import co.leancode.add2appbackgroundservice.pigeon.Api

class ComputationServiceApiHandler(private val foregroundService: ComputationForegroundService) : Api.NativeBackgroundServiceApi {
    override fun stopService() {
        foregroundService.stopService()
    }

    override fun openDialog() {
        val intent = Intent(foregroundService, FlutterDialogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        foregroundService.startActivity(intent)
    }

    override fun updateNotification(notification: Api.ComputationNotification) {
        ComputationServiceNotification.updateNotification(foregroundService, notification)
    }
}