package com.a6.ensayoswifi.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("ipAdress") var ipAdress: String?,
    @SerializedName("Hardware") var hardware: String?,
    @SerializedName("version") var version: String?,
    @SerializedName("Software") var software: String?,
    @SerializedName("sensor1") var sensor1: String?,
    @SerializedName("sensor2") var sensor2: String?,
    @SerializedName("sensor3") var sensor3: String?,
    @SerializedName("sensor4") var sensor4: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    constructor(ipAdress: String?, hardware: String?, version: String?, software: String?):
            this(ipAdress, hardware, version, software, "none", "none", "none", "none")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ipAdress)
        parcel.writeString(hardware)
        parcel.writeString(version)
        parcel.writeString(software)
        parcel.writeString(sensor1)
        parcel.writeString(sensor2)
        parcel.writeString(sensor3)
        parcel.writeString(sensor4)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Device> {
        override fun createFromParcel(parcel: Parcel): Device {
            return Device(parcel)
        }

        override fun newArray(size: Int): Array<Device?> {
            return arrayOfNulls(size)
        }
    }

}