package com.a6.ensayoswifi.ui.contactora

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.databinding.FragmentContactoraBinding
import com.a6.ensayoswifi.ui.base.BaseFragment


class ContactoraFragment : BaseFragment<FragmentContactoraBinding>() {

    override fun getFragmentView() = R.layout.fragment_contactora

    private lateinit var device: Device
    private lateinit var viewModel:ContactoraViewModel

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        device = ContactoraFragmentArgs.fromBundle(requireArguments()).device

        viewModel = device.ipAdress?.let { ContactoraViewModel(it) }!!

        viewModel.mediciones.observe(viewLifecycleOwner, {
            val mediciones = viewModel.mediciones.value
            if (mediciones != null) {
                binding.textViewT1Name.text = mediciones[0].name + " = "
                binding.textViewT1Value.text = mediciones[0].value + mediciones[0].unit

                binding.textViewT2Name.text = mediciones[1].name + " = "
                binding.textViewT2Value.text = mediciones[1].value + mediciones[1].unit
            }
        })

        viewModel.getMediciones()

        binding.bottomActualizar.setOnClickListener {
            viewModel.getMediciones()
        }

    }

    override fun onResume() {
        super.onResume()

        device = ContactoraFragmentArgs.fromBundle(requireArguments()).device

        binding.bottomApagarContactora.setOnClickListener {
            viewModel.clrPin(PIN_CONTACTORA)
        }

        binding.bottomPrenderContactora.setOnClickListener {
            viewModel.setPin(PIN_CONTACTORA)
        }

        binding.bottomApagarLedVerde?.setOnClickListener {
            viewModel.clrPin(PIN_LED_VERDE)
        }

        binding.bottomPrenderLedVerde?.setOnClickListener {
            viewModel.setPin(PIN_LED_VERDE)
        }

        binding.bottomPrenderLedRojo?.setOnClickListener {
            viewModel.setPin(PIN_LED_ROJO)
        }

        binding.bottomApagarRojo?.setOnClickListener {
            viewModel.clrPin(PIN_LED_ROJO)
        }

        binding.bottomBuscarDispocitivos.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.buscarDispocitivos)
        }

    }

    companion object {
        const val PIN_LED_ROJO = "26"
        const val PIN_LED_VERDE = "25"
        const val PIN_CONTACTORA = "12"
    }

}