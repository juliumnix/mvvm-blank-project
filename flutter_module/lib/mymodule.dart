import 'package:flutter/services.dart';

class MyModule {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  Future<String> getMessageFromNative() async {
    try {
      final String message = await platform.invokeMethod('getMessage');
      return message;
    } on PlatformException catch (e) {
      return '';
    }
  }
}
