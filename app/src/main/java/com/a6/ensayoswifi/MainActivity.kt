package com.a6.ensayoswifi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.InputDevice
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a6.ensayoswifi.data.MedicionRepository
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista = getGameControllerIds()

        Log.d(TAG, lista.toString())



    }

    private val dpad = Dpad()

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
        when ( event.action ) {
            ACTION_MOVE -> {
                val rawZ = event.getAxisValue(AXIS_Z)
                val rawRZ = event.getAxisValue(AXIS_RZ)
                val axisR = event.getAxisValue(AXIS_RTRIGGER)
                val axisL = event.getAxisValue(AXIS_LTRIGGER)
                Log.d(TAG, "Movimiento: X=${event.rawX} Y=${event.rawY } Z=${rawZ} RX=${rawRZ}  R=${axisR}   L=${axisL}")
            }
            ACTION_HOVER_MOVE -> {
                Log.d(TAG, "Mouse pad: X=${event.rawX}  Y =${event.rawY}")
            }
        }
        return true
    }


    override fun dispatchGenericMotionEvent(ev: MotionEvent?): Boolean {
        return super.dispatchGenericMotionEvent(ev)
    }

    override fun onTrackballEvent(event: MotionEvent?): Boolean {
        return super.onTrackballEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyDown $keyCode ${event.toString()}" )
        return true
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d(TAG, "onKeyUp $keyCode ${event.toString()}" )
        return true
    }





    fun getGameControllerIds(): List<Int> {
        val gameControllerDeviceIds = mutableListOf<Int>()
        val deviceIds = InputDevice.getDeviceIds()
        deviceIds.forEach { deviceId ->
            InputDevice.getDevice(deviceId).apply {

                // Verify that the device has gamepad buttons, control sticks, or both.
                if (sources and InputDevice.SOURCE_GAMEPAD == InputDevice.SOURCE_GAMEPAD
                    || sources and InputDevice.SOURCE_JOYSTICK == InputDevice.SOURCE_JOYSTICK) {
                    // This device is a game controller. Store its device ID.
                    gameControllerDeviceIds
                        .takeIf { !it.contains(deviceId) }
                        ?.add(deviceId)
                }
            }
        }
        return gameControllerDeviceIds
    }


    companion object {
        const val TAG = "TAGGG"
    }





}

