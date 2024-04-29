package me.liolin.app_badge_plus.badge

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.util.Log
import me.liolin.app_badge_plus.impl.DefaultBadge
import java.util.Collections


class Badge {

    companion object {
        private const val TAG = "Badge"
        private var BADGES: MutableList<Class<out IBadge>> = mutableListOf()
        private var iBadge: IBadge? = null
        private var iComponentName: ComponentName? = null

        init {
            BADGES.add(DefaultBadge::class.java)
        }

        fun updateBadge(context: Context, count: Int) {
            try {
                updateBadgeOrThrow(context, count)
            } catch (e: BadgeException) {
                Log.e(TAG, "Unable to update badge", e)
            }
        }

        @Throws(BadgeException::class)
        fun updateBadgeOrThrow(context: Context, count: Int) {
            if (iBadge == null) {
                val launcherReady: Boolean = initBadge(context)
                if (!launcherReady) {
                    throw BadgeException("No default launcher available")
                }
            }
            if (iComponentName == null) {
                throw BadgeException("Unable to get ComponentName")
            }
            try {
                iBadge?.updateBadge(context, iComponentName!!, count)
            } catch (e: Exception) {
                throw BadgeException("Unable to update badge", e)
            }
        }

        private fun initBadge(context: Context): Boolean {
            val launchIntent: Intent? =
                context.packageManager.getLaunchIntentForPackage(context.packageName)
            if (launchIntent == null) {
                Log.e(TAG, "Unable to find launch intent for package: " + context.packageName)
                return false
            }

            iComponentName = launchIntent.component

            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            val resolveList: List<ResolveInfo> = context.packageManager.queryIntentActivities(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            val defaultActivity: ResolveInfo? =
                context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            makeSureOnTop(defaultActivity, resolveList)

            for (resolveInfo in resolveList) {
                val activityInfo = resolveInfo.activityInfo.packageName
                Log.i(TAG, "Checking launcher $activityInfo")

                for (badge in BADGES) {
                    var tempBadge: IBadge? = null
                    try {
                        tempBadge = badge.getDeclaredConstructor().newInstance()
                    } catch (ignore: Exception) {
                    }
                    if (tempBadge != null &&
                        tempBadge.getSupportLaunchers().contains(activityInfo)
                    ) {
                        iBadge = tempBadge
                        break
                    }
                }
                if (iBadge != null) {
                    break
                }
            }

            return true
        }

        /**
         * Make sure the home activity is on top of the list
         *
         */
        private fun makeSureOnTop(defaultActivity: ResolveInfo?, resolveList: List<ResolveInfo>) {
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
}