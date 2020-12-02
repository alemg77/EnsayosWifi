package com.a6.ensayoswifi.network

import com.a6.ensayoswifi.model.Device

class Repository(private val baseURL:String) {

    suspend fun getGeneric(url:String): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL+url)
        return retrofitBuilder.getGeneric("/")
    }

    suspend fun getDevice(url:String): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL+url)
        val device = retrofitBuilder.getDevice("/id")
        if ( device is State.Success ){
            (device.data as Device).ipAdress = baseURL+url
        }
        return device
    }

    suspend fun getTemp(): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL)
        return retrofitBuilder.getTemp("/temp")
    }

}