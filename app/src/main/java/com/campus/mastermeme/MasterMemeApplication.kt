package com.campus.mastermeme

import android.app.Application
import com.campus.mastermeme.edit.di.editModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MasterMemeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MasterMemeApplication)
            // Load modules
            modules(
                editModule
            )
        }
    }
}