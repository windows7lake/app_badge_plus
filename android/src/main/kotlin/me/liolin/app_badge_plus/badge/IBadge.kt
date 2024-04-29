package me.liolin.app_badge_plus.badge

import android.content.ComponentName
import android.content.Context

interface IBadge {

    /**
     * Update notification badge count
     *
     * @param context     context
     * @param componentName component containing package and class name of calling application's launcher activity
     * @param count       badge count
     * @throws BadgeException
     */
    @Throws(BadgeException::class)
    fun updateBadge(context: Context, componentName: ComponentName, count: Int)

    /**
     * Get launchers which support badge
     *
     * @return supported launchers package name list
     */
    fun getSupportLaunchers(): List<String>
}