package com.a6.ensayoswifi.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.data.model.Medicion
import com.a6.ensayoswifi.data.room.MedicionDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicionRepository(app: Application) : ViewModel() {

    private val db = MedicionDatabase.getInstance(app)

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val all = db?.medicionDao()?.getAll()
            Log.d("TAGGG", all.toString())
        }
    }

    fun insertAll(vararg medicion: Medicion){
        viewModelScope.launch(Dispatchers.IO) {
            db?.medicionDao()?.insertAll(*medicion)
        }
    }


}