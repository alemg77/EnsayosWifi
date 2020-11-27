package com.a6.ensayoswifi

import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = MainViewModel()

        mainViewModel.test()

        mainViewModel.checkControl.observe(this, {
            textViewEncontrados.text = mainViewModel.devices.size.toString()
            val number = mainViewModel.noDevices.size + mainViewModel.devices.size
            textViewCheckControl.text = number.toString()
        })


        Log.d(TAG, "Fin del OnCreate")

    }



    companion object {
        const val TAG = "TAGGG"
    }
}