package com.a6.ensayoswifi.ui.temperatura

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.databinding.FragmentTemperatureBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


class TempFragment : BaseFragment<FragmentTemperatureBinding>(), KoinComponent {

    override fun getFragmentView() = R.layout.fragment_temperature

    private lateinit var ipAdress: String

    private var tInicio: Long = 0
    private var tFinal: Long = 0
    private var tTotal: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ipAdress = TempFragmentArgs.fromBundle(requireArguments()).ip

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = TempViewModelFactory(ipAdress)
        val viewModel: TempViewModel by viewModels() { viewModelFactory }
        viewModel.getTemps()

        viewModel.temp.observe(viewLifecycleOwner, {
            val value = viewModel.temp.value
            if (value != null) {

                /*
                val applicationContext = activity?.applicationContext

                if (applicationContext != null) {
                    val dbHelper =  DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
                    lifecycleScope.launch {
                        dbHelper.insert(value)
                    }
                }

                 */




                binding.textViewT1Name.text = value.t1Name + " = "
                binding.textViewT2Name.text = value.t2Name + " = "
                binding.textViewT3Name.text = value.t3Name + " = "
                binding.textViewT4Name.text = value.t4Name + " = "

                binding.textViewT1Value.text = value.t1 + "°C"
                binding.textViewT2Value.text = value.t2 + "°C"
                binding.textViewT3Value.text = value.t3 + "°C"
                binding.textViewT4Value.text = value.t4 + "°C"
            }
            tFinal = System.currentTimeMillis()
            tTotal = tFinal - tInicio


        })

        binding.bottomActualizar.setOnClickListener {
            tInicio = System.currentTimeMillis()
            viewModel.getTemps()
        }

        binding.bottomBuscarDispocitivos.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.buscarDispocitivos)
        }


    }

}