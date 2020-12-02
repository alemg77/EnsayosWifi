package com.a6.ensayoswifi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MedicionBEModel(
    @SerializedName("T1") val t1: String,
    @SerializedName("T1Name") val t1Name:String,
    @SerializedName("T1Unidades") val t1Unidades:String,
    @SerializedName("T2") val t2: String,
    @SerializedName("T2Name") val t2Name:String,
    @SerializedName("T2Unidades") val t2Unidades:String,
    @SerializedName("T3") val t3: String,
    @SerializedName("T3Name") val t3Name:String,
    @SerializedName("T3Unidades") val t3Unidades:String,
    @SerializedName("T4") val t4: String,
    @SerializedName("T4Name") val t4Name:String,
    @SerializedName("T4Unidades") val t4Unidades:String,
) {
    @PrimaryKey(autoGenerate = true) val id:Int? = null
}
