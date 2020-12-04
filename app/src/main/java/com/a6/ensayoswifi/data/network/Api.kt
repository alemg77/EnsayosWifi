package com.a6.ensayoswifi.data.network

import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.data.model.MedicionBEModel
import com.a6.ensayoswifi.data.model.MedicionesBEModel
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun fetchGeneric(
        @Url url:String
    ): Any

    @GET
    suspend fun fetchDevice(
        @Url url:String
    ): Device

    @GET
    suspend fun fetchMediciones(
        @Url url: String
    ): MedicionesBEModel


}