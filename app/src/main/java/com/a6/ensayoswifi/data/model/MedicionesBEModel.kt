package com.a6.ensayoswifi.data.model

import com.google.gson.annotations.SerializedName

data class MedicionesBEModel(
    @SerializedName ("mediciones") val mediciones: List<MedicionBEModel>
)
