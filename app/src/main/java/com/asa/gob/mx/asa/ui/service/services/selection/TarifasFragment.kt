package com.asa.gob.mx.asa.ui.service.services.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asa.gob.mx.asa.databinding.FragmentTarifasBinding
import com.asa.gob.mx.asa.utils.configWeb


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

}