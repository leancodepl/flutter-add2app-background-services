import Foundation
import Flutter

class NativeMainApiHandler : NSObject, LNCDNativeMainApi {
    private var engine: FlutterEngine?
    private let computationService: ComputationService
    
    init(engine: FlutterEngine, computationService: ComputationService) {
        self.computationService = computationService
        super.init()

        LNCDNativeMainApiSetup(engine.binaryMessenger, self)
    }
    
    func startServiceNotification(_ notification: LNCDComputationNotification, error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        computationService.start(notification: notification)
    }
    
    func stopServiceWithError(_ error: AutoreleasingUnsafeMutablePointer<FlutterError?>) {
        computationService.stop()
    }
}
