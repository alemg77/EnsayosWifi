package com.a6.ensayoswifi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.a6.ensayoswifi.data.MedicionRepository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val medicionRepository: MedicionRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        medicionRepository.getAll()



    }

    companion object {
        const val TAG = "TAGGG"
    }
}

