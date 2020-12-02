package com.a6.ensayoswifi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicion(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Value") val value: String,
    @ColumnInfo(name = "Unit") val unit: String
)