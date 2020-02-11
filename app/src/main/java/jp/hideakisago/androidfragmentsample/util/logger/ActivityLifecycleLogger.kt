package jp.hideakisago.androidfragmentsample.util.logger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import timber.log.Timber

class ActivityLifecycleLogger : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) =
        log(activity, "onActivityCreated savedInstanceState:$savedInstanceState")

    override fun onActivityStarted(activity: Activity) =
        log(activity, "onActivityStarted")

    override fun onActivityResumed(activity: Activity) =
        log(activity, "onActivityResumed")

    override fun onActivityPaused(activity: Activity) =
        log(activity, "onActivityPaused")

    override fun onActivityStopped(activity: Activity) =
        log(activity, "onActivityStopped")

    override fun onActivityDestroyed(activity: Activity) =
        log(activity, "onActivityDestroyed")

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) =
        log(activity, "onActivitySaveInstanceState")

    private fun log(activity: Activity, message: String) =
        Timber.tag(activity.javaClass.simpleName).v(message)
}
