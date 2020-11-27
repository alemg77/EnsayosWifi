package com.a6.ensayoswifi.network.model

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("ipAdress") var ipAdress: String,
    @SerializedName("Hardware") val hardware: String,
    @SerializedName("version") val version: String,
    @SerializedName("Software") val software: String
)