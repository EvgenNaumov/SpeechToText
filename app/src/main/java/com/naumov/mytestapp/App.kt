package com.naumov.mytestapp

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.naumov.mytestapp.network.ManagerNetworkConnect

class App : Application() {

    companion object {

        private var _context: Context? = null
        val cont: Context
            get() = _context!!

        private var _resours: Resources? = null
        val resours: Resources
            get() = _resours!!

        private var _instanceNet: ManagerNetworkConnect? = null
        val instanceNet: ManagerNetworkConnect
            get() = _instanceNet!!

//        fun getCont():Context = cont
//        fun getNet():ManagerNetwork = instanceNet
    }

    override fun onCreate() {
        super.onCreate()
        _context = applicationContext
        _resours = applicationContext.resources
        _instanceNet = ManagerNetworkConnect.instanceNet(cont)

    }


    override fun onTerminate() {
        super.onTerminate()
        _context = null
    }
}
