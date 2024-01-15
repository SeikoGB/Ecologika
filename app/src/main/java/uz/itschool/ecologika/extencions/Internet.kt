package uz.itschool.ecologika.extencions

import android.content.Context
import android.net.ConnectivityManager

fun hasNetworkConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}