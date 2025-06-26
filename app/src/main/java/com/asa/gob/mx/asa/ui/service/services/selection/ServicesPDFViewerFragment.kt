package com.asa.gob.mx.asa.ui.service.services.selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentServicesPdfViewerBinding
import com.asa.gob.mx.asa.utils.Constants
import com.asa.gob.mx.asa.utils.dialogOK

class ServicesPDFViewerFragment : Fragment() {

    private var _binding: FragmentServicesPdfViewerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServicesPdfViewerBinding.inflate(inflater, container, false)

        val args = ServicesPDFViewerFragmentArgs.fromBundle(requireArguments())
        val show = args.show
        try {
            val pdfView = binding.pdfViewer
            pdfView.fromAsset(when(show){
                Constants.SHOWSERVAERO ->{
                    Constants.PDF_SERVAERO
                }
                Constants.SHOWCOMBUStibles ->  {
                    Constants.PDF_COMBUStibles
                }
                Constants.SHOWCONSULTORIA -> {
                    Constants.PDF_CONSULTORIA
                }
                Constants.SHOWRENTROOMCIIASA -> {
                    Constants.PDF_RENTROOMCIIASA
                }
                else -> {
                    ""
                }
            }).fitsSystemWindows = true
            pdfView.show()
        }catch(e: Exception){
            dialogOK(requireContext(),getString(R.string.err_title),e.message.toString())
        }finally {
            binding.pbLoading.visibility = View.GONE
        }
        return binding.root
    }

}