package com.a6.ensayoswifi.ui.buscar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.network.Repository
import com.a6.ensayoswifi.network.State
import com.a6.ensayoswifi.network.model.Device
import com.a6.ensayoswifi.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BuscarDispocitivosViewModel : ViewModel() {


    private val repository = Repository(getBaseURL())

    var devices = arrayListOf<Device>()
    var noDevices = arrayListOf<Int>()

    private val _checkControl = MutableLiveData<Int>()
    val checkControl: LiveData<Int> = _checkControl

    private val _newDevices = MutableLiveData<Boolean>()
    val newDevices: LiveData<Boolean> = _newDevices

    init {
        _checkControl.postValue(0)
    }

    private fun getBaseURL(): String {
        val ipAddress = Utils.getIPAddress(true)
        return "http://" + ipAddress.dropLastWhile { it.isLetterOrDigit() }
    }

    private var testInProgress:Boolean = false

    fun test() {

        if (testInProgress) {
            return
        }

        testInProgress = true
        viewModelScope.launch(Dispatchers.IO) {

            devices = arrayListOf()
            noDevices = arrayListOf()
            _checkControl.postValue(0)
            for (i in 1..255) {
                test(i)
                delay(15)
            }
        }

    }

    private fun test(number:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getDevice(number.toString())){
                is State.Success -> {
                    devices.add(response.data as Device)
                    _newDevices.postValue(true)
                    Log.d(TAG, "Success")
                }
                is State.Error -> {
                    noDevices.add(number)
                }
                else -> {
                    noDevices.add(number)
                }
            }
            _checkControl.postValue(1 + _checkControl.value!!)
        }
    }

    private fun registrarDispocitivo(number: Int, map: Map<String, String>) {
        val hardware = map.get("Hardware")

        if ( hardware == "Epaper" ){

        }

    }

    companion object {
        const val TAG = "TAGGG"
    }
}