package co.leancode.add2appbackgroundservice

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

enum class AppFlutterEngine(val id: String) {
    main("MAIN"),
    computationService("COMPUTATION_SERVICE"),
    dialog("DIALOG")
}

class FlutterUtils {
    companion object {
        private const val LOG_TAG = "FlutterUtils"

        private lateinit var engineGroup: FlutterEngineGroup

        fun initialize(context: Context): FlutterEngine {
            engineGroup = FlutterEngineGroup(context)
        }

        fun createOrGetEngine(
            context: Context,
            engine: AppFlutterEngine,
            dartEntrypoint: DartExecutor.DartEntrypoint,
        ): FlutterEngine {
            var cachedEngine = FlutterEngineCache.getInstance().get(engine.id)
            if (cachedEngine == null) {
                Log.d(LOG_TAG, "Engine ${engine.id} does not exist, creating engine")
                cachedEngine = engineGroup.createAndRunEngine(context, dartEntrypoint)
                FlutterEngineCache.getInstance().put(engine.id, cachedEngine)
            }
            return cachedEngine!!
        }

        fun getEngine(engine: AppFlutterEngine): FlutterEngine? {
            return FlutterEngineCache.getInstance().get(engine.id)
        }

        fun destroyEngine(engine: AppFlutterEngine) {
            val cachedEngine = getEngine(engine)
            cachedEngine?.let {
                FlutterEngineCache.getInstance().remove(engine.id)
                it.destroy()
                Log.d(LOG_TAG, "Destroyed engine ${engine.id}")
            }
        }

        fun createRootView(context: Context): ViewGroup {
            val view = LinearLayout(context)
            view.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            view.orientation = LinearLayout.VERTICAL
            return view
        }
    }
}