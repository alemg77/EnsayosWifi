package com.a6.ensayoswifi.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("ipAdress") var ipAdress: String?,
    @SerializedName("Hardware") val hardware: String?,
    @SerializedName("version") val version: String?,
    @SerializedName("Software") val software: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ipAdress)
        parcel.writeString(hardware)
        parcel.writeString(version)
        parcel.writeString(software)
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