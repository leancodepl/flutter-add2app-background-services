import 'dart:isolate';

import 'package:flutter_module/src/common/isolates/isolate_client.dart';
import 'package:flutter_module/src/common/isolates/isolate_host.dart';
import 'package:flutter_module/src/common/isolates/register_isolate.dart';
import 'package:flutter_module/src/gen/api.dart';

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
  BackgroundServiceIsolateHost._(ReceivePort port, this._api)
      : super(receivePort: port, isolateName: _isolateName);

  final NativeBackgroundServiceApi _api;

  factory BackgroundServiceIsolateHost.register(
      NativeBackgroundServiceApi api) {
    return BackgroundServiceIsolateHost._(registerIsolate(_isolateName), api);
  }

  @override
  Future<void> dispose() async {
    await _api.stopService();
    await super.dispose();
  }
}
