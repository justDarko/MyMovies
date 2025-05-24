package com.solo.mymovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.d("The base url is: ${BuildConfig.BASE_URL}")
        Timber.d("The access token is: ${BuildConfig.ACCESS_TOKEN}")
    }
}