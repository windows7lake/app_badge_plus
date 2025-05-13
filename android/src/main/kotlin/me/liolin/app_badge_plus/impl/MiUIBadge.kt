package me.liolin.app_badge_plus.impl

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.util.Log
import me.liolin.app_badge_plus.badge.Badge
import me.liolin.app_badge_plus.badge.IBadge

import androidx.annotation.Keep;

@Keep
class MiUIBadge : IBadge {
    override fun updateBadge(context: Context, count: Int) {
        if (Badge.notification == null) return
        val notification = Badge.notification!!
        notificationBadge(count, notification)
    }

    @SuppressLint("PrivateApi")
    private fun notificationBadge(count: Int, notification: Notification) {
        try {
            val field = notification.javaClass.getDeclaredField("extraNotification")
            val extraNotification = field[notification]
            val method = extraNotification.javaClass.getDeclaredMethod(
                "setMessageCount",
                Int::class.javaPrimitiveType
            )
            method.invoke(extraNotification, count)
        } catch (e: Exception) {
            Log.e("Badge", "Unable to update badge for MiUI", e)
        }
    }

    override fun getSupportLaunchers(): List<String> {
        return listOf(
            "com.miui.miuilite",
            "com.miui.home",
            "com.miui.miuihome",
            "com.miui.miuihome2",
            "com.miui.mihome",
            "com.miui.mihome2",
            "com.i.miui.launcher"
        )
    }
}