package com.a6.ensayoswifi.ui.temperatura

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.Navigation
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.data.MedicionRepository
import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.databinding.FragmentTemperatureBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent


class MedicionFragment : BaseFragment<FragmentTemperatureBinding>(), KoinComponent {

    override fun getFragmentView() = R.layout.fragment_temperature

    private val repository: MedicionRepository by inject()

    private lateinit var device: Device
    private lateinit var ipAdrees: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        device = MedicionFragmentArgs.fromBundle(requireArguments()).device

        ipAdrees = device.ipAdress.toString()

    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.textViewTitulo.text = device.hardware
        
        val viewModel = MedicionesViewModel(ipAdrees)
        viewModel.getMediciones()

        viewModel.mediciones.observe(viewLifecycleOwner, {
            val mediciones = viewModel.mediciones.value
            if (mediciones != null) {
                // Guardo en room
                repository.insertAll(mediciones)

                binding.textViewT1Name.text = mediciones[0].name + " = "
                binding.textViewT2Name.text = mediciones[1].name + " = "
                binding.textViewT3Name.text = mediciones[2].name + " = "
                binding.textViewT4Name.text = mediciones[3].name + " = "

                binding.textViewT1Value.text = mediciones[0].value + mediciones[0].unit
                binding.textViewT2Value.text = mediciones[1].value + mediciones[1].unit
                binding.textViewT3Value.text = mediciones[2].value + mediciones[2].unit
                binding.textViewT4Value.text = mediciones[3].value + mediciones[3].unit

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