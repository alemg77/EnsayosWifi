package com.a6.ensayoswifi

import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.network.Repository
import com.a6.ensayoswifi.network.State
import com.a6.ensayoswifi.network.model.Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){


    private val repository = Repository(getBaseURL())

    var devices = arrayListOf<Device>()
    var noDevices = arrayListOf<Int>()

    private val _checkControl = MutableLiveData<Int>()
    val checkControl: LiveData<Int> = _checkControl

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
                    Log.d(TAG, "Success")
                }
                is State.Error -> {
                    Log.d(TAG, "posicion $number , Error")
                    noDevices.add(number)
                }
                else -> {
                    Log.d(TAG, "posicion $number , ??")
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

//val map = response.data as Map<String, String>

/*
fun test() {
    val wm = applicationContext.getSystemService(AppCompatActivity.WIFI_SERVICE) as WifiManager
    val ipAddress = wm.connectionInfo.ipAddress

    val formatIpAddress = Formatter.formatIpAddress(ipAddress)

    val dropLastWhile = formatIpAddress.dropLastWhile { it.isLetterOrDigit() }
    val dropLast = dropLastWhile.dropLast(1)

    val macAddress = Utils.getMACAddress("wlan0")
    val macAddress1 = Utils.getMACAddress("eth0")
    val ip = Utils.getIPAddress(true) // IPv4
}

fun ipStringToArrayString ( ip:String): ArrayList<String> {
    val indexOf = ip.indexOf(".", 0)

    val ipPrimerNumero = ip.substring(0, indexOf)

    val formatIpAddress2 = ip.substring(indexOf+1)

    val indexOf_2 = formatIpAddress2.indexOf(".", 0)

    val ipSegundoNumero = formatIpAddress2.substring(0, indexOf_2)

    val formatIpAddress3 = formatIpAddress2.substring(indexOf_2+1)

    val indexOf_3 = formatIpAddress3.indexOf(".", 0)

    val ipTercerNumero = formatIpAddress3.substring(0, indexOf_3)

    val ipCuartoNumero = formatIpAddress3.substring(indexOf_3+1)

    return arrayListOf(ipPrimerNumero, ipSegundoNumero, ipTercerNumero, ipCuartoNumero)
}

 */
