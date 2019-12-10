import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:immediate_login/immediate_login.dart';

void main() {
  const MethodChannel channel = MethodChannel('immediate_login');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ImmediateLogin.login(), '42');
  });
}
