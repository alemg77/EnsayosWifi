package com.a6.ensayoswifi.ui.buscar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a6.ensayoswifi.databinding.DeviceItemBinding
import com.a6.ensayoswifi.network.model.Device

class DeviceAdapter(private var dataSet: ArrayList<Device>,  private val listener:DeviceListener) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private lateinit var binding: DeviceItemBinding

    override fun getItemCount() = dataSet.size

    fun actualizarDevices(dataSetNew: ArrayList<Device>) {
        dataSet = dataSetNew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.actualizarCelda(dataSet[position])
    }

    inner class ViewHolder(private val binding: DeviceItemBinding, private val listener:DeviceListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.layoutDevice.setOnClickListener {
                listener.deviceOnClick(adapterPosition)
            }
        }

        fun actualizarCelda(device: Device) {
            binding.hardwareValue.text = device.hardware
            binding.softwareValue.text = device.software
            binding.versionValue.text = device.version
            binding.ipValue.text = device.ipAdress
        }
    }

    interface DeviceListener{
        fun deviceOnClick (position: Int)
    }

}

