package com.test.glexercise

import android.app.Application
import com.test.glexercise.injection.module.AppModule
import com.test.glexercise.injection.module.NetworkModule
import com.test.glexercise.injection.module.RepositoryModule
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule, NetworkModule, RepositoryModule)
        }
        instance = this
    }
}