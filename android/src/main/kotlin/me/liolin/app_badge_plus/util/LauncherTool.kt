package me.liolin.app_badge_plus.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import java.util.Collections

object LauncherTool {
    private const val TAG = "Badge"

    fun getClassName(context: Context): String {
        val launchComponent = getComponentName(context)
        return launchComponent?.className ?: ""
    }

    fun getComponentName(context: Context): ComponentName? {
        val launchIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        return launchIntent?.component
    }

    fun getLauncherList(context: Context): List<ResolveInfo?> {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val resolveList: List<ResolveInfo> = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        val defaultActivity: ResolveInfo? =
            context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        makeSureLauncherOnTop(defaultActivity, resolveList)
        return resolveList
    }

    /**
     * Make sure the home activity is on top of the list
     *
     */
    private fun makeSureLauncherOnTop(
        defaultActivity: ResolveInfo?,
        resolveList: List<ResolveInfo>
    ) {
        var indexToSwap = 0
        for (i in resolveList.indices) {
            val resolveInfo = resolveList[i]
            val activityInfo = resolveInfo.activityInfo.packageName
            Log.i(TAG, "package $i: $activityInfo")
            if (activityInfo == defaultActivity?.activityInfo?.packageName) {
                indexToSwap = i
            }
        }
        Collections.swap(resolveList, 0, indexToSwap)
    }
}