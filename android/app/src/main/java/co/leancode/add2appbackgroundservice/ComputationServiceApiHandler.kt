package co.leancode.add2appbackgroundservice

import android.content.Intent
import co.leancode.add2appbackgroundservice.pigeon.Api
import io.flutter.embedding.engine.FlutterEngine

class ComputationServiceApiHandler(private val foregroundService: ComputationForegroundService) : Api.NativeBackgroundServiceApi {
    override fun stopService() {
        foregroundService.stopService()
    }

    override fun openDialog(data: Api.DialogData) {
        val intent = Intent(foregroundService, FlutterDialogActivity::class.java)
        val engine = FlutterUtils.createOrGetEngine(foregroundService, AppFlutterEngine.dialog)
        foregroundService.startActivity(intent)
    }

    override fun updateNotification(notification: Api.ComputationNotification) {
        ComputationServiceNotification.updateNotification(foregroundService, notification)
    }
}