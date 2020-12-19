package com.a6.ensayoswifi.data.network

import com.a6.ensayoswifi.data.model.Device
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitBuilder(baseURL:String) {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        //.addInterceptor(loggingInterceptor)
        .build()

    private val buildRetrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val api = buildRetrofit.create(Api::class.java)

    suspend fun getGeneric(url:String): State<out Any> {
        return try {
            val fetchGeneric = api.fetchGeneric(url)
            State.Success(fetchGeneric)
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

    suspend fun getDevice(url:String): State<out Any> {
        return try {
            val fetchGeneric = api.fetchDevice(url)
            State.Success(fetchGeneric)
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

    suspend fun updateConfig(device: Device): State<out Any>{
        return try {
            val jsonDevice: String = Gson().toJson(device)
            val body = jsonDevice.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val fetchGeneric = api.updateConfig("gigigi", "1234", body)
            State.Success(fetchGeneric)
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

    suspend fun getMediciones(url:String): State<out Any> {
        return try {
            val fetchGeneric = api.fetchMediciones(url)
            State.Success(fetchGeneric)
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

    suspend fun setPin(pinNumber:String): State<out Any> {
        return try {
            State.Success(api.setPin(pinNumber))
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

    suspend fun clrPin(pinNumber:String): State<out Any> {
        return try {
            State.Success(api.clrPin(pinNumber))
        } catch (e:Throwable) {
            when (e) {
                is IOException -> {
                    State.Error(IOException())
                }
                is HttpException -> {
                    State.Error(e.code())
                }
                else -> {
                    State.Error()
                }
            }
        }
    }

}