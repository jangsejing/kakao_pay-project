package com.jess.kp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * @author jess
 * @since 2020.06.12
 */
class JessApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return DaggerAppComponent.factory().create(this)
//    }
}
