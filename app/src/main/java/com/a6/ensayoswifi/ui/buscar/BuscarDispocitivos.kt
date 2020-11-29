package com.a6.ensayoswifi.ui.buscar

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.databinding.BuscarDispocitivosFragmentBinding
import com.a6.ensayoswifi.ui.base.BaseFragment

class BuscarDispocitivos : BaseFragment<BuscarDispocitivosFragmentBinding>() {

    override fun getFragmentView() = R.layout.buscar_dispocitivos_fragment

    private lateinit var viewModel: BuscarDispocitivosViewModel

    private lateinit var deviceAdapter:DeviceAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(BuscarDispocitivosViewModel::class.java)

        deviceAdapter = DeviceAdapter(viewModel.devices)
        binding.RecyclerViewDispocitivos.layoutManager = LinearLayoutManager(activity)
        binding.RecyclerViewDispocitivos.adapter = deviceAdapter

        viewModel.test()

        observarViewModel()

    }

    private fun observarViewModel() {
        viewModel.checkControl.observe(viewLifecycleOwner, {
            val number = viewModel.noDevices.size + viewModel.devices.size
            binding.textViewContadorBuscados.text = number.toString()
            binding.textViewContadorEncontrados.text = viewModel.devices.size.toString()
        })

        viewModel.newDevices.observe(viewLifecycleOwner, {
            deviceAdapter.actualizarDevices(viewModel.devices)
        })
    }

}