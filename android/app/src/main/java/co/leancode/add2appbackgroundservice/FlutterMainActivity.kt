package co.leancode.add2appbackgroundservice

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.LinearLayout
import co.leancode.add2appbackgroundservice.pigeon.Api
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment

class FlutterMainActivity : AppCompatActivity() {
    companion object {
        private const val FLUTTER_FRAGMENT_TAG = "main_fragment"
    }

    private var flutterFragment: FlutterFragment? = null
    private var nativeMainApiHandler: NativeMainApiHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = FlutterUtils.createRootView(this)
        setContentView(root)

        val flutterEngine = FlutterUtils.createOrGetEngine(this, AppFlutterEngine.main)
        flutterFragment = FlutterUtils.attachFlutterFragment(
            this,
            root,
            supportFragmentManager,
            FLUTTER_FRAGMENT_TAG,
            AppFlutterEngine.main
        )

        nativeMainApiHandler =  NativeMainApiHandler(this)
        Api.NativeMainApi.setup(flutterEngine.dartExecutor, nativeMainApiHandler)
    }

    override fun onNewIntent(intent: Intent?) {
        if (intent != null) {
            flutterFragment?.onNewIntent(intent)
        }
        super.onNewIntent(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        flutterFragment?.onBackPressed()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        flutterFragment?.onUserLeaveHint()
    }

    override fun onDestroy() {
        flutterFragment?.detachFromFlutterEngine()
        nativeMainApiHandler?.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        flutterFragment?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}