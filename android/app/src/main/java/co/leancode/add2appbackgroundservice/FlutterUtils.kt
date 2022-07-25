package co.leancode.add2appbackgroundservice

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.android.TransparencyMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.FlutterEngineGroup
import io.flutter.embedding.engine.dart.DartExecutor

enum class AppFlutterEngine(val id: String, val entrypoint: String) {
    main("MAIN", "main"),
    computationService("COMPUTATION_SERVICE", "backgroundServiceMain"),
    dialog("DIALOG", "dialogMain")
}

class FlutterUtils {
    companion object {
        private const val LOG_TAG = "FlutterUtils"

        private lateinit var engineGroup: FlutterEngineGroup

        fun initialize(context: Context) {
            engineGroup = FlutterEngineGroup(context)
        }

        fun createOrGetEngine(
            context: Context,
            engine: AppFlutterEngine,
        ): FlutterEngine {
            var cachedEngine = FlutterEngineCache.getInstance().get(engine.id)
            if (cachedEngine == null) {
                Log.d(LOG_TAG, "Engine ${engine.id} does not exist, creating engine")

                val entrypoint = DartExecutor.DartEntrypoint("lib/main.dart", engine.entrypoint)
                cachedEngine = engineGroup.createAndRunEngine(context, entrypoint)
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

        fun attachFlutterFragment(
            context: Context,
            root: ViewGroup,
            fragmentManager: FragmentManager,
            fragmentTag: String,
            flutterEngine: AppFlutterEngine,
        ): FlutterFragment {
            var flutterFragment = fragmentManager.findFragmentByTag(fragmentTag) as FlutterFragment?

            if (flutterFragment == null) {
                flutterFragment = FlutterFragment
                    .withCachedEngine(flutterEngine.id)
                    .shouldAttachEngineToActivity(true)
                    .renderMode(RenderMode.surface)
                    .transparencyMode(TransparencyMode.opaque)
                    .build()

                fragmentManager
                    .beginTransaction()
                    .add(R.id.flutter_fragment_id, flutterFragment, fragmentTag)
                    .commit()
            }

            val flutterContainer = FrameLayout(context)
            root.addView(flutterContainer)
            flutterContainer.id = R.id.flutter_fragment_id
            flutterContainer.layoutParams = LinearLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT,
                1.0f
            )

            return flutterFragment
        }
    }
}