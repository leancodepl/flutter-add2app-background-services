import UIKit
import Flutter
import FlutterPluginRegistrant

@main
class AppDelegate: FlutterAppDelegate {
    lazy var flutterBridge = FlutterBridge()

    override func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let flutterEngine = flutterBridge.createOrGetEngine(engine: .main)
        flutterEngine.run()
        
        GeneratedPluginRegistrant.register(with: flutterEngine)
        _ = NativeMainApiHandler(engine: flutterEngine, computationService: ComputationService(flutterBridge: flutterBridge))
        
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
}

