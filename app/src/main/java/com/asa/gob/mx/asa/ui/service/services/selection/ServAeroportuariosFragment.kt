package com.asa.gob.mx.asa.ui.service.services.selection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asa.gob.mx.asa.data.remote.model.ServicesDto
import com.asa.gob.mx.asa.databinding.FragmentServAeroportuariosBinding
import com.asa.gob.mx.asa.ui.adapters.ServicesAdapter
import com.asa.gob.mx.asa.utils.Constants
import kotlinx.coroutines.launch

class ServAeroportuariosFragment : Fragment() {

    private var _binding: FragmentServAeroportuariosBinding? = null
    private val binding get() = _binding!!

    private var services: List<ServicesDto> = listOf(
        ServicesDto("1","","Title1"),
        ServicesDto("2","","Title2")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServAeroportuariosBinding.inflate(inflater, container, false)


        lifecycleScope.launch {
            try{
                val service = services

                binding.rvServicesAeroOp.apply {
                    layoutManager = GridLayoutManager(requireContext()
                        , 2)
                    adapter = ServicesAdapter(service){ selectedServices ->

                    }
                }

                binding.rvServicesAeroComp.apply {
                    layoutManager = GridLayoutManager(requireContext()
                        , 2)
                    adapter = ServicesAdapter(service){ selectedServices ->

                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                binding.pbLoading.visibility = View.GONE
            }
        }

        binding.btnShowMore.setOnClickListener {
            val action = ServAeroportuariosFragmentDirections.actionServAeroportuariosFragmentToServicesPDFViewerFragment(Constants.SHOWSERVAERO)
            findNavController().navigate(action)
        }

        return binding.root
    }

}