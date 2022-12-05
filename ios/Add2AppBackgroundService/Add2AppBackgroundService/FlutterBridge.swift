import Foundation
import Flutter

enum AppFlutterEngine: String {
    case main = "main"
    case computationService = "backgroundServiceMain"
    case dialog = "dialogMain"
}

class FlutterBridge {
    private let engineGroup: FlutterEngineGroup = FlutterEngineGroup(name: "FlutterGroup", project: nil)
    private var engines: [String: FlutterEngine] = [String: FlutterEngine]()
    
    public func createOrGetEngine(engine: AppFlutterEngine) -> FlutterEngine {
        let entrypoint = engine.rawValue
        let cachedEngine = engines[entrypoint]
        if cachedEngine != nil {
            return engines[entrypoint]!
        }
        
        let newEngine = engineGroup.makeEngine(withEntrypoint: entrypoint, libraryURI: nil)
        engines[entrypoint] = newEngine
        return newEngine
    }
    
    public func destroyEngine(engine: AppFlutterEngine) {
        engines[engine.rawValue]?.destroyContext()
        engines[engine.rawValue] = nil
    }
    
    public func createFlutterViewController(engine: AppFlutterEngine) -> UIViewController {
        let flutterEngine = createOrGetEngine(engine: engine)
        let viewController = FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
        viewController.modalPresentationStyle = .fullScreen
        return viewController
    }
}
