import 'package:flutter/material.dart';
import 'package:flutter_module/src/gen/api.dart';

class MainPage extends StatelessWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Flutter Main'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            NativeMainApi().startService(
              ComputationNotification(
                title: 'Computation service is running',
                message: '',
                percentProgress: 0,
              ),
            );
          },
          child: Text('Start computation'.toUpperCase()),
        ),
      ),
    );
  }
}
