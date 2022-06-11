package com.kproject.programmingjokes

import android.app.Application
import com.kproject.programmingjokes.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                databaseModule,
                networkModule,
                useCasesModule,
                infrastructureModule,
                presentationModule
            )
        }
    }
}