package com.a6.ensayoswifi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a6.ensayoswifi.data.MedicionRepository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    lateinit var wifiManager: WifiManager

    private val medicionRepository: MedicionRepository by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    companion object {
        const val TAG = "TAGGG"
    }





}

