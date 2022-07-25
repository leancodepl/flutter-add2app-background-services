import 'dart:isolate';
import 'dart:ui';

import 'package:flutter/foundation.dart';

abstract class IsolateClient<T> {
  IsolateClient(this._isolateName);

  final String _isolateName;

  @protected
  SendPort? get sendPort => IsolateNameServer.lookupPortByName(_isolateName);

  void send(T message) => sendPort?.send(message);
}
