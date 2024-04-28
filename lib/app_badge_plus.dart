
import 'app_badge_plus_platform_interface.dart';

class AppBadgePlus {
  Future<String?> getPlatformVersion() {
    return AppBadgePlusPlatform.instance.getPlatformVersion();
  }
}
