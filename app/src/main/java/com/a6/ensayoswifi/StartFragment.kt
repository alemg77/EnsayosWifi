package com.a6.ensayoswifi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.navigation.Navigation
import com.a6.ensayoswifi.databinding.FragmentStartBinding
import com.a6.ensayoswifi.ui.base.BaseFragment
import com.a6.ensayoswifi.utils.SharedPreferencesManager
import com.a6.ensayoswifi.utils.networkMonitor.NetWorkMonitorViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject


class StartFragment : BaseFragment<FragmentStartBinding>(), KoinComponent {

    override fun getFragmentView() = R.layout.fragment_start

    private val sharedPreferencesManager: SharedPreferencesManager by inject()
    private val networkMonitorViewModel: NetWorkMonitorViewModel by inject()

    private var wifiAvailable: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (isWifeAvailable(context)) {
            wifiAvailable = true
        }
    }

    override fun onStart() {
        super.onStart()

        if (wifiAvailable) {
            goToAction()
        }

        networkMonitorViewModel.wifiEnabled.observe(viewLifecycleOwner, {
            if (it) {
                goToAction()
            }
        })

    }

    private fun goToAction() {
        val device = sharedPreferencesManager.getSavedDevice()
        if (device.ipAdress == null) {
            Navigation.findNavController(binding.root).navigate(R.id.buscarDispocitivos)
        } else if (device.hardware.equals("Contactora")) {
            val action = StartFragmentDirections
                .actionStartFragmentToContactoraFragment(device)
            Navigation.findNavController(binding.root).navigate(action)
        } else {
            val action = StartFragmentDirections.actionStartFragmentToTemperatureFragment(device)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun isWifeAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                true
            }
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                false
            }
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                false
            }
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                false
            }
            else -> false
        }
    }

}