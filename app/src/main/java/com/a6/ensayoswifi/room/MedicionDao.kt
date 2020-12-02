package com.a6.ensayoswifi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MedicionDao {
    @Query("SELECT * FROM medicion")
    suspend fun getAll(): List<Medicion>

    @Insert
    suspend fun insertAll(vararg medicions: Medicion)

    @Delete
    suspend fun delete(medicion: Medicion)
}