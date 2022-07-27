package co.leancode.add2appbackgroundservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.leancode.add2appbackgroundservice.pigeon.Api
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine

class FlutterDialogActivity : AppCompatActivity() {
    companion object {
        private const val FLUTTER_FRAGMENT_TAG = "dialog_fragment"
    }

    private var flutterFragment: FlutterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val engine = FlutterUtils.createOrGetEngine(this, AppFlutterEngine.dialog)
        initializeFlutterApis(engine)

        val root = FlutterUtils.createRootView(this)
        setContentView(root)

        flutterFragment = FlutterUtils.attachFlutterFragment(
            this,
            root,
            supportFragmentManager,
            FlutterDialogActivity.FLUTTER_FRAGMENT_TAG,
            AppFlutterEngine.dialog
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        FlutterUtils.destroyEngine(AppFlutterEngine.dialog)
    }

    private fun initializeFlutterApis(flutterEngine: FlutterEngine) {
        Api.NativeDialogApi.setup(flutterEngine.dartExecutor, NativeDialogApiHandler(this))
    }
}