package jp.hideakisago.androidfragmentsample

import android.app.Application
import jp.hideakisago.androidfragmentsample.util.logger.ActivityLifecycleLogger
import jp.hideakisago.androidfragmentsample.util.logger.FragmentLifecycleLoggerRegister
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        registerActivityLifecycleCallbacks(ActivityLifecycleLogger())
        registerActivityLifecycleCallbacks(FragmentLifecycleLoggerRegister())
    }
}
