package com.a6.ensayoswifi.application

import android.app.Application
import com.a6.ensayoswifi.utils.networkMonitor.NetWorkMonitorViewModel
import com.a6.ensayoswifi.data.MedicionRepository
import com.a6.ensayoswifi.utils.SharedPreferencesManager
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class KoinApplication : Application() {

    private val myModule: Module = module {
        factory { SharedPreferencesManager(androidApplication()) }
        factory { MedicionRepository(androidApplication()) }
        single { NetWorkMonitorViewModel() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(myModule)
        }
    }

}

