package com.a6.ensayoswifi.UI.buscar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a6.ensayoswifi.R
import com.a6.ensayoswifi.UI.base.BaseFragment
import com.a6.ensayoswifi.databinding.BuscarDispocitivosFragmentBinding

class BuscarDispocitivos : BaseFragment<BuscarDispocitivosFragmentBinding>() {

    override fun getFragmentView() = R.layout.buscar_dispocitivos_fragment

    private lateinit var viewModel: BuscarDispocitivosViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuscarDispocitivosViewModel::class.java)

        viewModel.test()

        observarViewModel()

    }

    private fun observarViewModel() {
        viewModel.checkControl.observe(viewLifecycleOwner, {
            val number = viewModel.noDevices.size + viewModel.devices.size
            binding.textViewContadorBuscados.text = number.toString()
            binding.textViewContadorEncontrados.text = viewModel.devices.size.toString()
        })
    }

}