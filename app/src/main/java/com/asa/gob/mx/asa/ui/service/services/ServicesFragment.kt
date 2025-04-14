package com.asa.gob.mx.asa.ui.service.services


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.ui.MainActivity
import com.asa.gob.mx.asa.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        binding.apply {
            btnTarifas.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.action_nav_services_to_tarifasFragment)
            }
            btnCIIASA.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.action_nav_services_to_CIIASAFragment)
            }
            btnAeropuertos.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.action_nav_services_to_servAeroportuariosFragment)
            }
            btnCombustible.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.action_nav_services_to_combustiblesFragment)
            }
            btnConsultoria.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.action_nav_services_to_consultoriaFragment)
            }

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}