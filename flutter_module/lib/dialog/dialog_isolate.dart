import 'dart:isolate';

import 'package:flutter_module/common/isolates/isolate_client.dart';
import 'package:flutter_module/common/isolates/isolate_host.dart';
import 'package:flutter_module/common/isolates/register_isolate.dart';

const _isolateName = 'dialogIsolate';

abstract class DialogIsolateMessage {
  const DialogIsolateMessage();
}

class ShowDialogRequest extends DialogIsolateMessage {
  const ShowDialogRequest();
}

class DialogIsolateClient extends IsolateClient<DialogIsolateMessage> {
  DialogIsolateClient() : super(_isolateName);
}

class DialogIsolateHost extends IsolateHost<DialogIsolateMessage> {
  DialogIsolateHost._(ReceivePort port)
      : super(receivePort: port, isolateName: _isolateName);

  factory DialogIsolateHost.register() {
    return DialogIsolateHost._(registerIsolate(_isolateName));
  }
}
