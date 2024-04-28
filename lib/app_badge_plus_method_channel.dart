import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'app_badge_plus_platform_interface.dart';

/// An implementation of [AppBadgePlusPlatform] that uses method channels.
class MethodChannelAppBadgePlus extends AppBadgePlusPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('app_badge_plus');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
