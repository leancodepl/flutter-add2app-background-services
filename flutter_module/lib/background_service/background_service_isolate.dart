import 'dart:isolate';

import 'package:flutter_module/common/isolates/isolate_client.dart';
import 'package:flutter_module/common/isolates/isolate_host.dart';
import 'package:flutter_module/common/isolates/register_isolate.dart';

const _isolateName = 'backgroundServiceIsolate';

abstract class BackgroundServiceMessage {
  const BackgroundServiceMessage();
}

class BackgroundServiceIsolateClient
    extends IsolateClient<BackgroundServiceMessage> {
  BackgroundServiceIsolateClient() : super(_isolateName);
}

class BackgroundServiceIsolateHost
    extends IsolateHost<BackgroundServiceMessage> {
  BackgroundServiceIsolateHost._(ReceivePort port)
      : super(receivePort: port, isolateName: _isolateName);

  factory BackgroundServiceIsolateHost.register() {
    return BackgroundServiceIsolateHost._(registerIsolate(_isolateName));
  }
}
