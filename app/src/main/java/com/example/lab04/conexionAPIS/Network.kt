package com.example.lab04.conexionAPIS

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

class Network {

    companion object {
        fun avaliableRed(activity: AppCompatActivity):Boolean{
            val connectivityManeger = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManeger.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

}