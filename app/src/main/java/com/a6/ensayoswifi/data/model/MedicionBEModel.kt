package com.a6.ensayoswifi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MedicionBEModel(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String,
    @SerializedName("units") val unit: String,
    @SerializedName("time") val time: String,
)


