package co.leancode.add2appbackgroundservice

import android.app.Application;

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FlutterUtils.initialize(applicationContext)
        FlutterUtils.createOrGetEngine(applicationContext, AppFlutterEngine.main)
    }
}