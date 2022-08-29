import 'package:pigeon/pigeon.dart';

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
  void openDialog();
  void updateNotification(ComputationNotification notification);
}

class ComputationNotification {
  late final String title;
  late final String message;
  late final int percentProgress;
}
