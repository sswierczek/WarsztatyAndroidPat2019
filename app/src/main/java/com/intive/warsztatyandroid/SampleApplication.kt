package com.intive.warsztatyandroid

import android.app.Application
import com.intive.warsztatyandroid.di.appModule
import org.koin.android.ext.android.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this,
            modules = listOf(appModule)
        )
    }
}