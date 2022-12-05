import UIKit

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    @IBAction func onOpenFlutterScreen(_ sender: Any) {
        let vc = (UIApplication.shared.delegate as! AppDelegate).flutterBridge.createFlutterViewController(engine: .main)
        present(vc, animated: true, completion: nil)
    }    
}
