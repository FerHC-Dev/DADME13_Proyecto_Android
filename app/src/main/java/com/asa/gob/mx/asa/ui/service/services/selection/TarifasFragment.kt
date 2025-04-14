package com.asa.gob.mx.asa.ui.service.services.selection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.asa.gob.mx.asa.databinding.FragmentTarifasBinding


class TarifasFragment : Fragment() {

    private var _binding: FragmentTarifasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarifasBinding.inflate(inflater, container, false)

        val webTarifas = binding.webTarifas

        configWeb(webTarifas)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun configWeb(webTarifas: WebView){
        webTarifas.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }

        webTarifas.settings.builtInZoomControls = true //permitir pellizcar para hacer zoom
        webTarifas.settings.displayZoomControls = false //deshabilitar controles predeterminados en la pagina
        webTarifas.settings.loadWithOverviewMode = true  //reducir si el ancho del contenido es mayor que el ancho de la ventana
        //webTarifas.settings.javaScriptEnabled = true
        webTarifas.loadUrl("https://google.com.mx")
    }

}