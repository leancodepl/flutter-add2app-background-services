import UIKit
import Flutter
import FlutterPluginRegistrant

@main
class AppDelegate: FlutterAppDelegate {
    lazy var flutterEngine = FlutterEngine(name: "main")
    lazy var flutterEngineGroup = FlutterEngineGroup(name: "FlutterGroup", project: nil)

    override func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        flutterEngine.run()
        GeneratedPluginRegistrant.register(with: self.flutterEngine)
        
        _ = NativeMainApiHandler(engine: flutterEngine, computationService: ComputationService())
        
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
}

