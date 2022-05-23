import 'dart:async';
import 'dart:isolate';
import 'dart:ui';

import 'package:flutter/foundation.dart';

abstract class IsolateHost<T> {
  IsolateHost({
    required ReceivePort receivePort,
    required String isolateName,
  })  : _isolateName = isolateName,
        _receivePort = receivePort {
    _subscribePort();
  }

  final ReceivePort _receivePort;
  final String _isolateName;

  late StreamSubscription<dynamic> _portSubscription;
  final StreamController<T> _eventsController = StreamController.broadcast();

  @protected
  void emit(T event) {
    _eventsController.add(event);
  }

  Future<void> dispose() async {
    await _portSubscription.cancel();
    await _eventsController.close();
    IsolateNameServer.removePortNameMapping(_isolateName);
  }

  void _subscribePort() {
    _receivePort.listen((event) {
      if (event is T) {
        _eventsController.add(event);
      }
    });
  }
}
