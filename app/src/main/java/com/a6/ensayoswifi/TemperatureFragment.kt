package com.a6.ensayoswifi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a6.ensayoswifi.databinding.FragmentTemperatureBinding
import com.a6.ensayoswifi.ui.base.BaseFragment


class TemperatureFragment : BaseFragment<FragmentTemperatureBinding>() {

    override fun getFragmentView() = R.layout.fragment_temperature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val device = TemperatureFragmentArgs.fromBundle(requireArguments()).device
    }

}