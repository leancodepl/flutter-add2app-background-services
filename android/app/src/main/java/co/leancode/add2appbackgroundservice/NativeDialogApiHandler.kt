package co.leancode.add2appbackgroundservice

import android.app.Activity
import android.util.Log
import co.leancode.add2appbackgroundservice.pigeon.Api

class NativeDialogApiHandler(private val activity: Activity): Api.NativeDialogApi {
    companion object {
        private const val LOG_TAG = "NativeDialogApiHandler"
    }

    override fun closeDialog() {
        Log.d(LOG_TAG, "Closing dialog")
        activity.finish()
    }
}