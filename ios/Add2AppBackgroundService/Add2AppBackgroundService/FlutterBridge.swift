import Foundation
import Flutter

class FlutterBridge {
    private var engineGroup: FlutterEngineGroup
    
    init() {
        engineGroup = (UIApplication.shared.delegate as! AppDelegate).flutterEngineGroup
    }
    
    public func register() {
    }
    
    public static func createFlutterViewController() -> UIViewController {
        let flutterEngine = (UIApplication.shared.delegate as! AppDelegate).flutterEngine
        let viewController = FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
        viewController.modalPresentationStyle = .fullScreen
        return viewController
    }
}
