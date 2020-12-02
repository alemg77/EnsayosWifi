package com.a6.ensayoswifi.ui.temperatura

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.Navigation
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.data.MedicionRepository
import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.data.model.Medicion
import com.a6.ensayoswifi.databinding.FragmentTemperatureBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent


class TempFragment : BaseFragment<FragmentTemperatureBinding>(), KoinComponent {

    override fun getFragmentView() = R.layout.fragment_temperature

    private val repository: MedicionRepository by inject()

    private lateinit var device: Device
    private lateinit var ipAdrees: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        device = TempFragmentArgs.fromBundle(requireArguments()).device

        ipAdrees = device.ipAdress.toString()

    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.textViewTitulo.text = device.hardware
        
        val viewModel = MedicionesViewModel(ipAdrees)
        viewModel.getMediciones()

        viewModel.temp.observe(viewLifecycleOwner, {
            val value = viewModel.temp.value
            if (value != null) {

                val medicion = Medicion(value.t1Name, value.t1, value.t1Unidades)

                repository.insertAll(medicion)

                binding.textViewT1Name.text = value.t1Name + " = "
                binding.textViewT2Name.text = value.t2Name + " = "
                binding.textViewT3Name.text = value.t3Name + " = "
                binding.textViewT4Name.text = value.t4Name + " = "

                binding.textViewT1Value.text = value.t1 + value.t1Unidades
                binding.textViewT2Value.text = value.t2 + value.t2Unidades
                binding.textViewT3Value.text = value.t3 + value.t3Unidades
                binding.textViewT4Value.text = value.t4 + value.t4Unidades
            }
        })

        binding.bottomActualizar.setOnClickListener {
            viewModel.getMediciones()
        }

        binding.bottomBuscarDispocitivos.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.buscarDispocitivos)
        }


    }

}