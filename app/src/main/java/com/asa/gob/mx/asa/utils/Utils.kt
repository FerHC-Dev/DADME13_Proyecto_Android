package com.asa.gob.mx.asa.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.http.SslError
import android.util.Log
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.asa.gob.mx.asa.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


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

fun configWeb(webTarifas: WebView){

    webTarifas.requestFocus();
    webTarifas.settings.javaScriptEnabled = true;
    webTarifas.settings.mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
    webTarifas.settings.allowFileAccess = false
    webTarifas.settings.allowContentAccess = false
    webTarifas.settings.domStorageEnabled = false
    webTarifas.isSoundEffectsEnabled = true;
    webTarifas.settings.safeBrowsingEnabled = true;
    webTarifas.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK;
    webTarifas.settings.setSupportZoom(true)  //habilitar zoom en la vista web
    webTarifas.settings.builtInZoomControls = true //permitir pellizcar para hacer zoom
    webTarifas.settings.displayZoomControls = true //deshabilitar controles predeterminados en la pagina
    webTarifas.settings.loadWithOverviewMode = true  //reducir si el ancho del contenido es mayor que el ancho de la ventana
    webTarifas.loadUrl(Constants.URL_TARIFAS)

    webTarifas.webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view?.scrollTo(0, 0)
        }

        @SuppressLint("WebViewClientOnReceivedSslError")
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            //handler?.proceed()  // Para ignorar SSL
            handler?.cancel() // Para cancelar la conexión en caso de error SSL
        }
    }
}

suspend fun downloadFileToCache(
    context: Context,
    fileUrl: String,
    fileName: String
): File? = withContext(Dispatchers.IO) {
    try {
        val url = URL(fileUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.connect()

        if (connection.responseCode != HttpURLConnection.HTTP_OK) {
            return@withContext null
        }

        val inputStream = connection.inputStream
        val outputFile = File(context.cacheDir, fileName)

        FileOutputStream(outputFile).use { outputStream ->
            inputStream.copyTo(outputStream)
        }

        inputStream.close()
        return@withContext outputFile
    } catch (e: Exception) {
        e.printStackTrace()
        return@withContext null
    }
}



fun allowAllSSL() {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    try {
        val sc = SSLContext.getInstance("SSL")
        sc.init(null, trustAllCerts, SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
        HttpsURLConnection.setDefaultHostnameVerifier { _, _ -> true }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

