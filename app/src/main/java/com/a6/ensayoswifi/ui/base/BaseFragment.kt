package com.a6.ensayoswifi.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected var exitApp = false

    protected lateinit var binding: DB

    abstract fun getFragmentView(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, getFragmentView(), container, false
        )
        return binding.root
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (exitApp) {
                    exitProcess(0)
                } else {
                    exitApp = true

                    ViewUtils.displaySnackBar(
                        binding.root,
                        R.id.bottom_bar_indicator,
                        getString(R.string.confirmation_close)
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        run {
                            exitApp = false
                        }
                    }, 2000)

                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

     */

    companion object {
        const val TAG = "TAGGG"
    }
}