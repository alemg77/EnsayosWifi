package com.a6.ensayoswifi.data.workmanager

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