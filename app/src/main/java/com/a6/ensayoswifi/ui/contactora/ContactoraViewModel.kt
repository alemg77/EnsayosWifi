package com.a6.ensayoswifi.ui.contactora

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.data.model.Medicion
import com.a6.ensayoswifi.data.model.MedicionesBEModel
import com.a6.ensayoswifi.data.network.Repository
import com.a6.ensayoswifi.data.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactoraViewModel(baseUrl:String): ViewModel() {

    private val _mediciones = MutableLiveData<ArrayList<Medicion>>()
    val mediciones: LiveData<ArrayList<Medicion>> = _mediciones

    private val repository = Repository(baseUrl)

    fun setPin(pinNumber:String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.setPin(pinNumber)){
                is State.Success -> {
                }
            }
        }
    }

    fun clrPin(pinNumber:String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.clrPin(pinNumber)){
                is State.Success -> {
                }
            }
        }
    }

    fun getMediciones() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getMediciones()){
                is State.Success -> {
                    val medicionesBEModel = (response.data as MedicionesBEModel).mediciones
                    val arrayMediciones = ArrayList<Medicion>()
                    for ( medicionBEModel in medicionesBEModel) {
                        arrayMediciones.add(Medicion(medicionBEModel))
                    }
                    _mediciones.postValue(arrayMediciones)
                }
            }
        }
    }


}