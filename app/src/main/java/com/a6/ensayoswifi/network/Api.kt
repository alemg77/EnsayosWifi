package com.a6.ensayoswifi.network

import com.a6.ensayoswifi.model.Device
import com.a6.ensayoswifi.model.MedicionModel
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
    suspend fun fetchTemp(
        @Url url: String
    ): MedicionModel


}