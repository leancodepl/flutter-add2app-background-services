import 'dart:isolate';

import 'dart:ui';

ReceivePort registerIsolate(String name) {
  final sendPort = IsolateNameServer.lookupPortByName(name);

  if (sendPort != null) {
    IsolateNameServer.removePortNameMapping(name);
  }

  final receivePort = ReceivePort();
  IsolateNameServer.registerPortWithName(receivePort.sendPort, name);
  return receivePort;
}
