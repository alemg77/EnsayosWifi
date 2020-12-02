package com.a6.ensayoswifi.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.a6.ensayoswifi.data.model.Medicion

@Dao
interface MedicionDao {

    @Query("SELECT * FROM medicion")
    suspend fun getAll(): List<Medicion>

    @Insert
    suspend fun insertAll(vararg medicions: Medicion)

    @Delete
    suspend fun delete(medicion: Medicion)

}