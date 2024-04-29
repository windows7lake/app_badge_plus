import 'package:app_badge_plus/app_badge_plus.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on:'),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            AppBadgePlus.updateBadge(2);
          },
          child: const Icon(Icons.add),
        ),
      ),
    );
  }
}
