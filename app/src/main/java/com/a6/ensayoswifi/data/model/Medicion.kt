package com.a6.ensayoswifi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicion(
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Value") val value: String,
    @ColumnInfo(name = "Unit") val unit: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0 // or foodId: Int? = null
    @ColumnInfo(name = "time") var time: Long = System.currentTimeMillis()

}

