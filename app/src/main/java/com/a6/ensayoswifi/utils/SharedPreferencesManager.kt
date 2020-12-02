package com.a6.ensayoswifi.utils

import android.app.Application
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.a6.ensayoswifi.model.Device

class SharedPreferencesManager(app: Application) {

    private var lastIpCache: String? = null

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
        else sharedPreferences.getString(IP1, null)
    }

    fun SaveIp(device:Device){
        lastIpCache = device.ipAdress
        saveCache()
    }


    private fun saveCache() {
        with(sharedPreferences.edit()) {
            putString(IP1, lastIpCache)
            apply()
        }
    }

    companion object {
        const val IP1 = "token"
        const val TAG = "TAGGG"
        private const val PREFERENCES_FILE = "secret_shared_prefs"
    }
}
