package com.a6.ensayoswifi.utils

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.a6.ensayoswifi.data.model.Device

class SharedPreferencesManager(app: Application) {

    private var lastIpCache: String? = null
    private var lastHardwareCache: String? = null
    private var lastVersionCache: String? = null
    private var lastSoftwareCache: String? = null

    var masterKey = MasterKey.Builder(app, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    var sharedPreferences = EncryptedSharedPreferences.create(
        app,
        PREFERENCES_FILE,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun getSaveIp(): String? {
        return if (lastIpCache != null) lastIpCache
        else sharedPreferences.getString(IP, null)
    }

    fun getSavedDevice():Device {
        val ip = sharedPreferences.getString(IP, null)
        val hardware = sharedPreferences.getString(HARDWARE, null)
        val software = sharedPreferences.getString(SOFTWARE, null)
        val version = sharedPreferences.getString(VERSION, null)
        return Device(ip, hardware, version, software)
    }

    fun SaveDevice(device: Device) {
        lastIpCache = device.ipAdress
        lastHardwareCache = device.hardware
        lastSoftwareCache = device.software
        lastVersionCache = device.version
        saveCache()
    }

    private fun saveCache() {
        with(sharedPreferences.edit()) {
            putString(IP, lastIpCache)
            putString(HARDWARE, lastHardwareCache)
            putString(VERSION, lastVersionCache)
            putString(SOFTWARE, lastSoftwareCache)
            apply()
        }
    }

    companion object {
        const val IP = "token"
        const val HARDWARE = "hardware"
        const val SOFTWARE = "software"
        const val VERSION = "version"
        const val TAG = "TAGGG"
        private const val PREFERENCES_FILE = "secret_shared_prefs"
    }
}
