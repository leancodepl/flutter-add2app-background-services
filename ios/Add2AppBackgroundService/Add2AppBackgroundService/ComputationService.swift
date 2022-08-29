import UIKit
import Flutter

class ComputationService: NSObject, LNCDNativeBackgroundServiceApi, LNCDNativeDialogApi {
    private let backgroundTaskName = "computatationBackgroundTask"
    private let dartEntrypoint = "backgroundServiceMain"

    private var engine: FlutterEngine?
    private var backgroundTaskIdentifier: UIBackgroundTaskIdentifier = UIBackgroundTaskIdentifier.invalid
    private var lastNotification: LNCDComputationNotification?
    
    func start(notification: LNCDComputationNotification) {
        if engine == nil {
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            appDelegate.flutterEngineGroup.makeEngine(withEntrypoint: dartEntrypoint, libraryURI: nil)
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
        
    }
    
    func stopServiceWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        stop()
    }

    func updateNotificationNotification(_ notification: LNCDComputationNotification, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        
    }
    
    func closeDialogWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        
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
