package com.asa.gob.mx.asa.ui.service.services.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentCiiasaPdfViewerBinding
import com.asa.gob.mx.asa.utils.Constants
import com.asa.gob.mx.asa.utils.dialogOK
import java.io.File

class CiiasaPDFViewerFragment : Fragment() {

    private var _binding: FragmentCiiasaPdfViewerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCiiasaPdfViewerBinding.inflate(inflater, container, false)

        val args = CiiasaPDFViewerFragmentArgs.fromBundle(requireArguments())
        val file = File(args.file)
        try {
            val pdfView = binding.pdfViewer
            pdfView.fromFile(file)
                .fitsSystemWindows = true
            pdfView.show()
        }catch(e: Exception){
            dialogOK(requireContext(),getString(R.string.err_title),e.message.toString())
        }finally {
            binding.pbLoading.visibility = View.GONE
        }
        return binding.root
    }

}