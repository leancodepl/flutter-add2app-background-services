import 'dart:isolate';

import 'package:flutter/material.dart';
import 'package:flutter_module/src/common/isolates/isolate_client.dart';
import 'package:flutter_module/src/common/isolates/isolate_host.dart';
import 'package:flutter_module/src/common/isolates/register_isolate.dart';

const _isolateName = 'mainIsolate';

abstract class MainIsolateMessage {}

class BackgroundServiceStarted extends MainIsolateMessage {}

class MainComputationStatusMessage extends MainIsolateMessage {
  MainComputationStatusMessage({required this.progress});

  final double progress;
}

class MainIsolateClient extends IsolateClient<MainIsolateMessage> {
  MainIsolateClient() : super(_isolateName);
}

class MainIsolateHost extends IsolateHost<MainIsolateMessage> {
  MainIsolateHost._(ReceivePort port)
      : super(receivePort: port, isolateName: _isolateName);

  factory MainIsolateHost.register() {
    return MainIsolateHost._(registerIsolate(_isolateName));
  }
}
