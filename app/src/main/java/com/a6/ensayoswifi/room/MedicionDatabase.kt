package com.a6.ensayoswifi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Medicion::class],
    version = 1)

abstract class MedicionDatabase() : RoomDatabase() {
    abstract fun medicionDao(): MedicionDao

    companion object {
        private const val DATABASE_NAME = "ensayo"
        @Volatile
        private var INSTANCE: MedicionDatabase? = null

        fun getInstance(context: Context): MedicionDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MedicionDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}

