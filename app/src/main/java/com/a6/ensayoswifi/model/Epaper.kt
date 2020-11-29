package com.a6.ensayoswifi.model

import com.google.gson.annotations.SerializedName

data class Epaper(
    @SerializedName( "ipAdress") val ipAdress: String,
    @SerializedName("version") val version : String,
)

