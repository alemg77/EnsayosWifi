package com.a6.ensayoswifi.ui.temperatura

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a6.ensayoswifi.model.MedicionModel
import com.a6.ensayoswifi.network.Repository
import com.a6.ensayoswifi.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TempViewModel(baseUrl:String)  : ViewModel() {


    private val _temp = MutableLiveData<MedicionModel>()
    val temp: LiveData<MedicionModel> = _temp

    private val repository = Repository(baseUrl)

    fun getTemps() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getTemp()){
                is State.Success -> {
                    _temp.postValue(response.data as MedicionModel)
                }
            }
        }
    }


}