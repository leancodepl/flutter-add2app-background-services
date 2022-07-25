import 'package:pigeon/pigeon.dart';

@FlutterApi()
abstract class FlutterMainApi {}

@HostApi()
abstract class NativeMainApi {
  void startService(ComputationNotification notification);
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
  void updateNotification(ComputationNotification notification);
}

class DialogData {
  late final String? message;
}

class ComputationNotification {
  late final String title;
  late final String message;
  late final int percentProgress;
}
