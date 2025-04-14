package com.asa.gob.mx.asa.ui.service.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentTermsBinding
import com.asa.gob.mx.asa.utils.dialogOK


class TermsFragment : Fragment() {

    private var _binding: FragmentTermsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTermsBinding.inflate(inflater, container, false)

        try {
            val pdfView = binding.pdfViewer
            pdfView.fromAsset("terminos.pdf").fitsSystemWindows = true
            pdfView.show()
        }catch(e: Exception){
            dialogOK(requireContext(),getString(R.string.err_title),e.message.toString())
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}