import 'dart:async';

import 'package:flutter/services.dart';

class ImmediateLogin {
  static const MethodChannel _channel = const MethodChannel('immediate_login');

  static Future<String> login(String token) async {
    final String version = await _channel.invokeMethod('login', token);
    return version;
  }
}
