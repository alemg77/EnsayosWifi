package com.a6.ensayoswifi.utils.networkMonitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import org.koin.core.KoinComponent
import org.koin.core.inject


class NetworkMonitor: BroadcastReceiver(), KoinComponent {

    private val viewModel: NetWorkMonitorViewModel by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        val wifi = context!!.getSystemService(Context.WIFI_SERVICE) as WifiManager
        viewModel.wifiEnabled(wifi.isWifiEnabled)
    }

}