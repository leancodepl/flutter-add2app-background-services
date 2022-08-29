import Foundation
import Flutter

class FlutterBridge {
    private var mainEngine: FlutterEngine?
    
    public func register() {
        
    }
    
    public static func createFlutterViewController() -> UIViewController {
        let flutterEngine = (UIApplication.shared.delegate as! AppDelegate).flutterEngine
        return FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
    }
}
