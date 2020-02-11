package jp.hideakisago.androidfragmentsample.util.logger

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import timber.log.Timber

class FragmentLifecycleLoggerRegister : Application.ActivityLifecycleCallbacks {
    private val logger = FragmentLifecycleLogger()
    private val Activity.supportFragmentManager: FragmentManager?
        get() = (this as? FragmentActivity)?.supportFragmentManager

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activity.supportFragmentManager?.registerFragmentLifecycleCallbacks(logger, true)
    }

    override fun onActivityDestroyed(activity: Activity) {
        activity.supportFragmentManager?.unregisterFragmentLifecycleCallbacks(logger)
    }

    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
}

class FragmentLifecycleLogger : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) =
        log(f, "onFragmentAttached")

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) =
        log(f, "onFragmentCreated savedInstanceState:$savedInstanceState")

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentStarted")

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentResumed")

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentPaused")

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentStopped")

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentDestroyed")

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) =
        log(f, "onFragmentDetached")

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) =
        log(f, "onFragmentSaveInstanceState")

    private fun log(fragment: Fragment, message: String) =
        Timber.tag(fragment.javaClass.simpleName)
            .v("%s%s", message, parents(fragment))

    private fun parents(fragment: Fragment): String =
        " on " + (fragment.parentFragment
            ?.let { parent -> parent.javaClass.simpleName + parents(parent) }
            ?: fragment.context!!.javaClass.simpleName)
}
