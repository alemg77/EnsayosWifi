package com.a6.ensayoswifi.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Medicion::class],
    version = 1)

abstract class MedicionDatabase : RoomDatabase() {
    abstract fun medicionDao(): MedicionDao
}

