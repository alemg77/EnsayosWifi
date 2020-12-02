package com.a6.ensayoswifi.room

import android.app.Application
import androidx.room.Room
import org.koin.core.KoinComponent

class MedicionBuilder(application: Application) {

    val db = Room.databaseBuilder(
        application,
        MedicionDatabase::class.java,
        "ensayo"
    ).build()
}