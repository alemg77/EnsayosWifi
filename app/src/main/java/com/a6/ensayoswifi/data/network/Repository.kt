package com.a6.ensayoswifi.data.network

import com.a6.ensayoswifi.data.model.Device

class Repository(private val baseURL:String) {

    suspend fun getGeneric(url:String): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL+url)
        return retrofitBuilder.getGeneric("/")
    }

    suspend fun updateConfig(device: Device):State<out Any> {
        return RetrofitBuilder(baseURL).updateConfig(device)
    }

    suspend fun getDevice(num:String): State<out Any> {
        val url = "$baseURL$num:7777/"
        val retrofitBuilder = RetrofitBuilder(url)
        val device = retrofitBuilder.getDevice("/id")
        if ( device is State.Success ){
            (device.data as Device).ipAdress = baseURL+num
        }
        return device
    }

    suspend fun getId(): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL)
        val device = retrofitBuilder.getDevice("/id")
        if ( device is State.Success ){
            (device.data as Device).ipAdress = baseURL
        }
        return device
    }

    suspend fun getMediciones(): State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL)
        return retrofitBuilder.getMediciones("/mediciones")
    }

    suspend fun setPin(pinNumber:String):State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL)
        return retrofitBuilder.setPin(pinNumber)
    }

    suspend fun clrPin(pinNumber:String):State<out Any> {
        val retrofitBuilder = RetrofitBuilder(baseURL)
        return retrofitBuilder.clrPin(pinNumber)
    }

}