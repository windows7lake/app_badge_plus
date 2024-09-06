# app_badge_plus

A Flutter plugin for adding badges to your app icon.

![iOS](https://raw.githubusercontent.com/windows7lake/app_badge_plus/main/screenshot/iOS.png)
![pixel](https://raw.githubusercontent.com/windows7lake/app_badge_plus/main/screenshot/pixel.png)
![samsung](https://raw.githubusercontent.com/windows7lake/app_badge_plus/main/screenshot/samsung.png)

## Supported Platforms``
- iOS
- Android
    - Samsung (pass)
    - Oppo (pass)
    - Vivo (pass)
    - Huawei (pass)
    - Xiaomi
    - LG
    - Sony
    - HTC
    - ZTX

## Mark

### Android

Starting with Android 8.0 (API level 26), notification badges—also known as notification 
dots—appear on a launcher icon when the associated app has an active notification. Users can 
touch & hold the app icon to reveal the notifications, along with any app shortcuts.

https://developer.android.com/develop/ui/views/notifications/badges

Starting With Android13 (API level 33), notification runtime permission should be requested before setting the app badge.

Add the following permissions to `AndroidManifest.xml` according to the system you need to support:
```xml
<!-- Samsung -->
<uses-permission android:name="com.sec.android.provider.badge.permission.READ"/>
<uses-permission android:name="com.sec.android.provider.badge.permission.WRITE"/>

<!-- HTC -->
<uses-permission android:name="com.htc.launcher.permission.READ_SETTINGS"/>
<uses-permission android:name="com.htc.launcher.permission.UPDATE_SHORTCUT"/>

<!-- Sony -->
<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE"/>
<uses-permission android:name="com.sonymobile.home.permission.PROVIDER_INSERT_BADGE"/>

<!-- Apex -->
<uses-permission android:name="com.anddoes.launcher.permission.UPDATE_COUNT"/>

<!-- Solid -->
<uses-permission android:name="com.majeur.launcher.permission.UPDATE_BADGE"/>

<!-- Huawei -->
<uses-permission android:name="com.huawei.android.launcher.permission.CHANGE_BADGE" />
<uses-permission android:name="com.huawei.android.launcher.permission.READ_SETTINGS" />
<uses-permission android:name="com.huawei.android.launcher.permission.WRITE_SETTINGS" />
```

### iOS

On iOS, notification permission is required.

### permission_handler 

Using permission_handler package to manage permission on Android and iOS.

https://pub.dev/packages/permission_handler


## Usage

To use this plugin, add `app_badge_plus` as a [dependency in your pubspec.yaml file](https://flutter.io/platform-plugins/).

```yaml
dependencies:
  app_badge_plus: ^1.1.2
```

## Example

```dart
import 'package:app_badge_plus/app_badge_plus.dart';

// Set badge
AppBadgePlus.updateBadge(5);

// Remove badge
AppBadgePlus.updateBadge(0);

// Whether the launcher supports badges. It always returns true on iOS and it will return false on Android if the launcher doesn't support badges.
AppBadgePlus.isSupported();
```

## Compare

### flutter_app_badger

https://pub.dev/packages/flutter_app_badger

#### Set badge

```dart
FlutterAppBadger.updateBadgeCount(1);
```

replace to 


```dart
AppBadgePlus.updateBadge(1);
```

#### Remove badge

```dart
FlutterAppBadger.removeBadge();
```

replace to 


```dart
AppBadgePlus.updateBadge(0);
```

#### Device support

```dart
FlutterAppBadger.isAppBadgeSupported();
```

using dart API: 

```dart
Platform.isAndroid || Platform.isiOS
```







