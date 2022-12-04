import UIKit
import Flutter

class ComputationService: NSObject, LNCDNativeBackgroundServiceApi, LNCDNativeDialogApi {
    private let backgroundTaskName = "computatationBackgroundTask"
    private let dartEntrypoint = "backgroundServiceMain"

    private var engine: FlutterEngine?
    private var dialogEngine: FlutterEngine?
    private var backgroundTaskIdentifier: UIBackgroundTaskIdentifier = UIBackgroundTaskIdentifier.invalid
    private var lastNotification: LNCDComputationNotification?
    
    func start(notification: LNCDComputationNotification) {
        if engine == nil {
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            engine = appDelegate.flutterEngineGroup.makeEngine(withEntrypoint: dartEntrypoint, libraryURI: nil)
            engine!.run()
            
            LNCDNativeBackgroundServiceApiSetup(engine!.binaryMessenger, self)
        }
        startBackgroundTask()
        lastNotification = notification
    }
    
    func stop() {
        stopBackgroundTask()
        engine?.destroyContext()
        engine = nil
    }
    
    func openDialogWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        dialogEngine = (UIApplication.shared.delegate as! AppDelegate).flutterEngineGroup.makeEngine(withEntrypoint: "dialogMain", libraryURI: nil)
        let vc = FlutterViewController(engine: dialogEngine!, nibName: nil, bundle: nil)
        vc.modalPresentationStyle = .fullScreen
        
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
        
    }
    
    func closeDialogWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        dialogEngine?.destroyContext()
        dialogEngine = nil
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
