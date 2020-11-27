package com.a6.ensayoswifi.network

import com.a6.ensayoswifi.network.model.Device
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


}