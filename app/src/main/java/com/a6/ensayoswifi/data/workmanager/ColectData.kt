package com.a6.ensayoswifi.data.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.a6.ensayoswifi.data.MedicionRepository
import com.a6.ensayoswifi.data.model.Medicion
import com.a6.ensayoswifi.data.model.MedicionBEModel
import com.a6.ensayoswifi.data.model.MedicionesBEModel
import com.a6.ensayoswifi.data.network.Repository
import com.a6.ensayoswifi.data.network.RetrofitBuilder
import com.a6.ensayoswifi.data.network.State
import com.a6.ensayoswifi.ui.temperatura.MedicionesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.inject

/*
class ColectData (appContext: Context, workerParams: WorkerParameters, val ipAddress:String):
    Worker(appContext, workerParams), KoinComponent {


    override fun doWork(): Result {

        val medicionRepository : MedicionRepository by inject()

        GlobalScope.launch {
            val repository = Repository(ipAddress)
            when (val response = repository.getMediciones()){
                is State.Success -> {
                    val medicionesBE = (response.data as MedicionesBEModel).mediciones
                    for ( medicionBE in medicionesBE){
                        val medicion = Medicion(medicionBE)
                    }
                    /*
                    val medicion = Medicion(
                        medicionBEModel.t1Name,
                        medicionBEModel.t1Name,
                        medicionBEModel.t1Unidades
                    )
                    medicionRepository.insertAll(medicion)

                     */
                }
            }
        }


        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}

 */