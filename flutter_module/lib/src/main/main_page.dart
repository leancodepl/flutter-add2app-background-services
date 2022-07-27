import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_module/src/gen/api.dart';
import 'package:flutter_module/src/main/main_isolate.dart';

class MainPage extends StatefulWidget {
  const MainPage({
    Key? key,
    required this.mainIsolateHost,
  }) : super(key: key);

  final MainIsolateHost mainIsolateHost;

  @override
  State<StatefulWidget> createState() {
    return _MainPageState();
  }
}

class _MainPageState extends State<MainPage> {
  StreamSubscription? mainHostSubscription;
  NativeMainApi nativeMainApi = NativeMainApi();

  bool isComputationRunning = false;
  double progress = 0;

  @override
  void initState() {
    super.initState();

    mainHostSubscription = widget.mainIsolateHost.events.listen((event) {
      if (event is MainComputationStatusMessage) {
        setState(() {
          progress = event.progress;
        });
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    mainHostSubscription?.cancel();
  }

  void _onStartPressed() {
    nativeMainApi.startService(
      ComputationNotification(
        title: 'Computation service is running',
        message: '',
        percentProgress: 0,
      ),
    );

    setState(() {
      isComputationRunning = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Flutter Main'),
      ),
      body: Center(
        child: isComputationRunning
            ? Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  CircularProgressIndicator(value: progress),
                  const SizedBox(height: 16),
                  const Text('Computation running'),
                ],
              )
            : ElevatedButton(
                onPressed: _onStartPressed,
                child: Text('Start computation'.toUpperCase()),
              ),
      ),
    );
  }
}
