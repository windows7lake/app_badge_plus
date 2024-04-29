package me.liolin.app_badge_plus.impl

import android.content.ComponentName
import android.content.Context
import me.liolin.app_badge_plus.badge.IBadge

class DefaultBadge : IBadge {
    override fun updateBadge(context: Context, componentName: ComponentName, count: Int) {
        // Do nothing
        //
        // Starting with Android 8.0 (API level 26), notification badges—also known as notification
        // dots—appear on a launcher icon when the associated app has an active notification. Users
        // can touch & hold the app icon to reveal the notifications, along with any app shortcuts.
        //
        // These dots appear by default in launcher apps that support them, and there's nothing
        // your app needs to do. However, there might be situations in which you don't want the to
        // notification dot to appear or you want to control exactly which notifications appear there.
    }

    override fun getSupportLaunchers(): List<String> {
        return emptyList()
    }
}