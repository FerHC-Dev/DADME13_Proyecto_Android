package com.asa.gob.mx.asa.ui.service.services.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentCombustiblesBinding
import com.asa.gob.mx.asa.utils.Constants

class CombustiblesFragment : Fragment() {

    private var _binding: FragmentCombustiblesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCombustiblesBinding.inflate(inflater, container, false)



        binding.btnShowMore.setOnClickListener {
            val action = CombustiblesFragmentDirections.actionCombustiblesFragmentToServicesPDFViewerFragment(
                Constants.SHOWCOMBUStibles)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}