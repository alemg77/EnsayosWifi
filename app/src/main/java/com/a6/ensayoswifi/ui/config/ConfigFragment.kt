package com.a6.ensayoswifi.ui.config

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.data.model.Device
import com.a6.ensayoswifi.data.network.State
import com.a6.ensayoswifi.databinding.FragmentConfigBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import com.a6.ensayoswifi.ui.mediciones.MedicionFragmentArgs


class ConfigFragment : BaseFragment<FragmentConfigBinding>() {

    private lateinit var device: Device

    override fun getFragmentView() = R.layout.fragment_config

    private lateinit var viewModel:ConfigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        device = MedicionFragmentArgs.fromBundle(requireArguments()).device

        viewModel = device.ipAdress?.let { ConfigViewModel(it) }!!

        viewModel.getId()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomGuardar.setOnClickListener {
            device.hardware = binding.editTextHardware.text.toString()
            device.software = binding.editTextSoftware.text.toString()
            device.version = binding.editTextVersion.text.toString()
            device.sensor1 = binding.editTextSensor1.text.toString()
            device.sensor2 = binding.editTextSensor2.text.toString()
            device.sensor3 = binding.editTextSensor3.text.toString()
            device.sensor4 = binding.editTextSensor4.text.toString()
            viewModel.updateConfig(device)
        }

        viewModel.updateResult.observe(viewLifecycleOwner, {
            when (it ){
                is State.Success -> {
                    activity?.onBackPressed()
                }
                else -> {
                    Toast.makeText(context, "Error!!", Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.device.observe(viewLifecycleOwner, {
            val value = viewModel.device.value
            binding.editTextHardware.setText(value?.hardware)
            binding.editTextSoftware.setText(value?.software)
            binding.editTextVersion.setText(value?.version)
            binding.editTextSensor1.setText(value?.sensor1)
            binding.editTextSensor2.setText(value?.sensor2)
            binding.editTextSensor3.setText(value?.sensor3)
            binding.editTextSensor4.setText(value?.sensor4)
        })


    }


}