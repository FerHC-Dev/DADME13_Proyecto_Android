package com.asa.gob.mx.asa.utils

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.core.content.ContextCompat
import com.asa.gob.mx.asa.R
import com.google.android.material.snackbar.Snackbar

fun message(view : View, text : String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setTextColor(ContextCompat.getColor(view.context,R.color.white))
        .setBackgroundTint(ContextCompat.getColor(view.context,R.color.black))
        .show()
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
    return networkCapabilities != null && networkCapabilities.hasCapability(
        NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun dialogOK(context: Context, title : String, message : String){
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}
