package com.a6.ensayoswifi.ui.temperatura

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.data.model.MedicionBEModel
import com.a6.ensayoswifi.data.network.Repository
import com.a6.ensayoswifi.data.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MedicionesViewModel(baseUrl:String)  : ViewModel() {


    private val _temp = MutableLiveData<MedicionBEModel>()
    val temp: LiveData<MedicionBEModel> = _temp

    private val repository = Repository(baseUrl)

    fun getMediciones() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getMediciones()){
                is State.Success -> {
                    _temp.postValue(response.data as MedicionBEModel)
                }
            }
        }
    }


}