package com.a6.ensayoswifi.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
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

}