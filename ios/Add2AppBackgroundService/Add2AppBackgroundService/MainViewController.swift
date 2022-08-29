import UIKit

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func onOpenFlutterScreen(_ sender: Any) {
        let viewController = FlutterBridge.createFlutterViewController()
        present(viewController, animated: true, completion: nil)
    }    
}
