package com.a6.ensayoswifi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.a6.ensayoswifi.room.MedicionDatabase
import com.a6.ensayoswifi.room.Medicion
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            MedicionDatabase::class.java,
            "ensayo"
        ).build()

        val medicion1 = Medicion(1, "temp1", "27", "°C")
        val medicion2 = Medicion(2, "temp1", "27.3", "°C")
        val medicion3 = Medicion(3, "temp1", "27.6", "°C")

        lifecycleScope.launch {
            val all = db.medicionDao().getAll()

            Log.d("TAGGG", all.toString())
            /*
            db.medicionDao().insertAll(medicion1)
            db.medicionDao().insertAll(medicion2)
            db.medicionDao().insertAll(medicion3)
             */

        }
    }

    companion object {
        const val TAG = "TAGGG"
    }
}