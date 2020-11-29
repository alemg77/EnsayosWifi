package com.a6.ensayoswifi.ui.buscar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a6.ensayoswifi.databinding.DeviceItemBinding
import com.a6.ensayoswifi.network.model.Device

class DeviceAdapter(private var dataSet: ArrayList<Device>) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    lateinit var bindind: DeviceItemBinding

    override fun getItemCount() = dataSet.size

    fun actualizarDevices(dataSetNew: ArrayList<Device>) {
        dataSet = dataSetNew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        bindind = DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bindind)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.actualizarCelda(dataSet[position])
    }

    inner class ViewHolder(private val binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        override fun onClick(v: View?) {
            TODO("Funcion onClick sin implementar!!!")
        }

        fun actualizarCelda(device: Device) {
            binding.hardwareValue.text = device.hardware
            binding.softwareValue.text = device.software
            binding.versionValue.text = device.version
            binding.ipValue.text = device.ipAdress
        }
    }

}

