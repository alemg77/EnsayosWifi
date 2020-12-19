package com.a6.ensayoswifi.ui.mediciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TempViewModelFactory(private val baseUrl:String):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java).newInstance(baseUrl)
    }
}