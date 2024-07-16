import Flutter
import UIKit

public class AppBadgePlusPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "app_badge_plus", binaryMessenger: registrar.messenger())
    let instance = AppBadgePlusPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }
    
  public func enableNotification() {
    UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .sound, .badge]) { accepted, error in
      if (!accepted) {
        print("enableNotification failed")
      }
    }
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    enableNotification()
    switch call.method {
      case "updateBadge":
        let args = call.arguments as? Dictionary<String, Any>
        let count = (args?["count"] as? Int) ?? 0
        print("count: ", count)
        if #available(iOS 16.0, *) {
          UNUserNotificationCenter.current().setBadgeCount(count)
        } else {
          UIApplication.shared.applicationIconBadgeNumber = count;
        }
        result(nil)
      case "isSupported":
        result(true)
      default:
        result(FlutterMethodNotImplemented)
    }
  }
}
