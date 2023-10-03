package com.naumov.mytestapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.naumov.mytestapp.utils.DEBUG_TAG_NETWORK

class ManagerNetworkConnect(context: Context) {


    private val request = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
        .build()


    private val netCallbackWifi = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(DEBUG_TAG_NETWORK, "onAvailable: Wifi")
            isWifi = !isWifi
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isWifi = !isWifi
            Log.d(DEBUG_TAG_NETWORK, "onLost: Wifi")
        }

    }

    private val netCallbackMobile = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(DEBUG_TAG_NETWORK, "onAvailable: Mobile")
            isMobile = !isMobile
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(DEBUG_TAG_NETWORK, "onLost: Mobile")
            isMobile = !isMobile
        }

    }

init {
    connectivityManager = context.getSystemService(ConnectivityManager::class.java)

}
    fun getStatusCurrentNetwork():Boolean {
        return isWifi || isMobile
    }

    fun setRegisterStatusNetwork() {

        connectivityManager?.registerDefaultNetworkCallback(netCallbackMobile)
        connectivityManager?.registerNetworkCallback(request,netCallbackWifi)

    }

    fun deleteRegisterStatusNetwork() {

        connectivityManager?.unregisterNetworkCallback(netCallbackWifi)
        connectivityManager?.unregisterNetworkCallback(netCallbackMobile)
    }


    companion object {
        private var connectivityManager: ConnectivityManager?=null
        private var isWifi = false
        private var isMobile = false

        fun instanceNet(context: Context): ManagerNetworkConnect {

            return ManagerNetworkConnect(context)
        }
    }

}