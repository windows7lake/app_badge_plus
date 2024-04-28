import 'package:flutter_test/flutter_test.dart';
import 'package:app_badge_plus/app_badge_plus.dart';
import 'package:app_badge_plus/app_badge_plus_platform_interface.dart';
import 'package:app_badge_plus/app_badge_plus_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockAppBadgePlusPlatform
    with MockPlatformInterfaceMixin
    implements AppBadgePlusPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final AppBadgePlusPlatform initialPlatform = AppBadgePlusPlatform.instance;

  test('$MethodChannelAppBadgePlus is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelAppBadgePlus>());
  });

  test('getPlatformVersion', () async {
    AppBadgePlus appBadgePlusPlugin = AppBadgePlus();
    MockAppBadgePlusPlatform fakePlatform = MockAppBadgePlusPlatform();
    AppBadgePlusPlatform.instance = fakePlatform;

    expect(await appBadgePlusPlugin.getPlatformVersion(), '42');
  });
}
