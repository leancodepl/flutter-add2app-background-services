import 'dart:async';

import 'package:flutter_module/src/background_service/background_service_isolate.dart';
import 'package:flutter_module/src/gen/api.dart';
import 'package:flutter_module/src/main/main_isolate.dart';

void startBackgroundService() {
  print('Starting background service');

  ComputationNotification? notification = ComputationNotification(
    title: 'Computation service running',
    message: '',
    percentProgress: 0,
  );

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

    mainClient.send(
      MainComputationStatusMessage(
        progress: (allTicks - remainingTicks) / allTicks,
      ),
    );

    if (remainingTicks == 0) {
      timer.cancel();
      backgroundServiceHost.dispose();
    }
  });
}
