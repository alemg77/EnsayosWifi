package com.a6.ensayoswifi.ui.config

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.MainActivity.Companion.TAG
import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.data.model.Medicion
import com.a6.ensayoswifi.data.model.MedicionesBEModel
import com.a6.ensayoswifi.data.network.Repository
import com.a6.ensayoswifi.data.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigViewModel(baseUrl:String): ViewModel() {

    private var _updateResult = MutableLiveData<State<out Any>>()
    var updateResult: LiveData<State<out Any>> = _updateResult


    private var _device = MutableLiveData<Device>()
    var device: LiveData<Device> = _device

    private val repository = Repository(baseUrl)

    fun getId() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getId()){
                is State.Success -> {
                    _device.postValue(response.data as Device)
                }
            }
        }
    }

    fun updateConfig(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            _updateResult.postValue(repository.updateConfig(device))
        }
    }

}