import 'package:flutter/material.dart';
import 'package:flutter_module/src/background_service/start_background_service.dart';
import 'package:flutter_module/src/dialog/dialog_isolate.dart';
import 'package:flutter_module/src/dialog/dialog_page.dart';
import 'package:flutter_module/src/main/main_isolate.dart';
import 'package:flutter_module/src/main/main_page.dart';

final theme = ThemeData(
  primarySwatch: Colors.indigo,
);

void main() {
  WidgetsFlutterBinding.ensureInitialized();

  final mainHost = MainIsolateHost.register();

  final app = MaterialApp(
    title: 'Flutter Demo',
    theme: theme,
    home: MainPage(mainIsolateHost: mainHost),
  );

  runApp(app);
}

@pragma('vm:entry-point')
void backgroundServiceMain() {
  WidgetsFlutterBinding.ensureInitialized();

  startBackgroundService();
}

@pragma('vm:entry-point')
void dialogMain() {
  WidgetsFlutterBinding.ensureInitialized();

  DialogIsolateHost.register();

  final app = MaterialApp(
    title: 'Flutter Dialog',
    theme: theme,
    home: const DialogPage(),
  );

  runApp(app);
}
