import UIKit
import Flutter

class ComputationService: NSObject, LNCDNativeBackgroundServiceApi, LNCDNativeDialogApi {
    private let backgroundTaskName = "computatationBackgroundTask"

    private var engine: FlutterEngine?
    private var backgroundTaskIdentifier: UIBackgroundTaskIdentifier = UIBackgroundTaskIdentifier.invalid
    private let flutterBridge: FlutterBridge
    
    init(flutterBridge: FlutterBridge) {
        self.flutterBridge = flutterBridge
    }
    
    func start(notification: LNCDComputationNotification) {
        let engine = flutterBridge.createOrGetEngine(engine: .computationService)
        engine.run()
            
        LNCDNativeBackgroundServiceApiSetup(engine.binaryMessenger, self)
        startBackgroundTask()
    }
    
    func stop() {
        stopBackgroundTask()
        flutterBridge.destroyEngine(engine: .computationService)
    }
    
    func openDialogWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        let dialogEngine = flutterBridge.createOrGetEngine(engine: .dialog)
        dialogEngine.run()
        
        let vc = flutterBridge.createFlutterViewController(engine: .dialog)
        guard let rootViewController = (UIApplication.shared.connectedScenes.first?.delegate as? SceneDelegate)?.window?.rootViewController else {
            return
        }
        rootViewController.dismiss(animated: false, completion: nil)
        rootViewController.present(vc, animated: true, completion: nil)
    }
    
    func stopServiceWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        stop()
    }

    func updateNotificationNotification(_ notification: LNCDComputationNotification, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        // Not used on iOS, you could show a local notification here
    }
    
    func closeDialogWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        flutterBridge.destroyEngine(engine: .dialog)
    }
    
    private func startBackgroundTask() {
        stopBackgroundTask()
        backgroundTaskIdentifier = UIApplication.shared.beginBackgroundTask(withName: backgroundTaskName, expirationHandler: {
            self.stop()
        })
    }
    
    private func stopBackgroundTask() {
        if backgroundTaskIdentifier != UIBackgroundTaskIdentifier.invalid {
            UIApplication.shared.endBackgroundTask(backgroundTaskIdentifier)
            backgroundTaskIdentifier = UIBackgroundTaskIdentifier.invalid
        }
    }
}
