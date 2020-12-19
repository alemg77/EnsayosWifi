package com.a6.ensayoswifi.data.network

import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.data.model.MedicionBEModel
import com.a6.ensayoswifi.data.model.MedicionesBEModel
import okhttp3.RequestBody
import retrofit2.http.*

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

    @POST("/updateConfig")
    suspend fun updateConfig (
        @Header("username") username: String,
        @Header("password") password: String,
        @Body params: RequestBody
    )

    @POST("/SetPin")
    suspend fun setPin(
        @Query("Pin") pin: String
    )


    @POST("/ClrPin")
    suspend fun clrPin(
        @Query("Pin") pin: String
    )
}