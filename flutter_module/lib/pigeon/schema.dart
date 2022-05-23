import 'package:pigeon/pigeon.dart';

@FlutterApi()
abstract class FlutterMainApi {}

@HostApi()
abstract class NativeMainApi {
  void startService();
  void stopService();
}

@HostApi()
abstract class NativeDialogApi {
  void closeDialog();
}

@HostApi()
abstract class NativeBackgroundServiceApi {
  void stopService();

  void openDialog(DialogData data);
}

class DialogData {
  late final String? message;
}
