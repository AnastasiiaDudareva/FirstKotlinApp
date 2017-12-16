package com.ad.firstkotlinapp

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by anastasiia on 16.12.17.
 */
class NetManagerWrapper(private var applicationContext: Context) {
    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}