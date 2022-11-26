import 'dart:async';

import 'package:flutter_module/src/background_service/background_service_isolate.dart';
import 'package:flutter_module/src/gen/api.dart';
import 'package:flutter_module/src/main/main_isolate.dart';

void startBackgroundService() {
  print('Starting background service');

  final mainClient = MainIsolateClient();
  final nativeServiceApi = NativeBackgroundServiceApi();
  final backgroundServiceHost =
      BackgroundServiceIsolateHost.register(nativeServiceApi);

  backgroundServiceHost.events.listen((event) {
    print('Received event from native host $event');
  });

  const allTicks = 20;
  var remainingTicks = 20;

  Timer.periodic(const Duration(seconds: 1), (timer) {
    print('Timer tick $remainingTicks');

    remainingTicks--;

    final progress = (allTicks - remainingTicks) / allTicks;

    mainClient.send(
      MainComputationStatusMessage(
        progress: progress,
      ),
    );

    nativeServiceApi.updateNotification(
      ComputationNotification(
        title: 'Computation service is running',
        message: '',
        percentProgress: (progress * 100).round(),
      ),
    );

    if (remainingTicks == 0) {
      timer.cancel();
      nativeServiceApi.openDialog();
      backgroundServiceHost.dispose();
    }
  });
}
