package com.a6.ensayoswifi.utils.networkMonitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a6.ensayoswifi.data.network.State

class NetWorkMonitorViewModel : ViewModel() {

    private var _WifiEnabled = MutableLiveData<Boolean>()
    var wifiEnabled: LiveData<Boolean> = _WifiEnabled

    fun wifiEnabled(state:Boolean){
        _WifiEnabled.postValue(state)
    }


}