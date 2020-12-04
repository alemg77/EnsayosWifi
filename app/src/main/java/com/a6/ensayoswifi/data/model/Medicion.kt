package com.a6.ensayoswifi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicion(
    @ColumnInfo(name = "Name") var name: String,
    @ColumnInfo(name = "Value") var value: String,
    @ColumnInfo(name = "Units") var unit: String,
    @ColumnInfo(name = "time") var time: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(medicionBE: MedicionBEModel) : this(
        medicionBE.name,
        medicionBE.value,
        medicionBE.unit,
        medicionBE.time
    )

}

