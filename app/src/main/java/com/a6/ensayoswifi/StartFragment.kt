package com.a6.ensayoswifi

import androidx.navigation.Navigation
import com.a6.ensayoswifi.utils.SharedPreferencesManager
import com.a6.ensayoswifi.databinding.FragmentStartBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import org.koin.core.KoinComponent
import org.koin.core.inject


class StartFragment : BaseFragment<FragmentStartBinding>(), KoinComponent {

    override fun getFragmentView() = R.layout.fragment_start

    private val sharedPreferencesManager: SharedPreferencesManager by inject()

    override fun onStart() {
        super.onStart()

        val saveIp = sharedPreferencesManager.getSaveIp()

        if (saveIp == null) {
            Navigation.findNavController(binding.root).navigate(R.id.buscarDispocitivos)
        } else {
            val action = StartFragmentDirections.actionStartFragmentToTemperatureFragment(saveIp)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }


}