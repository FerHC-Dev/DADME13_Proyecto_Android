package com.asa.gob.mx.asa.ui.service.services.selection

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.data.remote.model.CoursesDto
import com.asa.gob.mx.asa.databinding.FragmentCiiasaBinding
import com.asa.gob.mx.asa.ui.adapters.CoursesAdapter
import com.asa.gob.mx.asa.utils.Constants
import com.asa.gob.mx.asa.utils.allowAllSSL
import com.asa.gob.mx.asa.utils.dialogOK
import com.asa.gob.mx.asa.utils.downloadFileToCache
import kotlinx.coroutines.launch

class CIIASAFragment : Fragment() {

    private var _binding: FragmentCiiasaBinding? = null
    private val binding get() = _binding!!

    private var services: List<CoursesDto> = listOf(
        CoursesDto("1","","Title1","https://ciiasa.asa.gob.mx/fichas-tecnicas/Factor-y-Desarrollo-Humano/Tecnicas_Didacticas.pdf","https://ciiasa.asa.gob.mx/calendarios/Calendario-SAFETY-2025.pdf"),
        CoursesDto("2","","Title2","https://ciiasa.asa.gob.mx/fichas-tecnicas/Factor-y-Desarrollo-Humano/Tecnicas_Didacticas.pdf","https://ciiasa.asa.gob.mx/calendarios/Calendario-SAFETY-2025.pdf"),
        CoursesDto("3","","Title3","https://ciiasa.asa.gob.mx/fichas-tecnicas/Factor-y-Desarrollo-Humano/Tecnicas_Didacticas.pdf","https://ciiasa.asa.gob.mx/calendarios/Calendario-SAFETY-2025.pdf"),
        CoursesDto("4","","Title4","https://ciiasa.asa.gob.mx/fichas-tecnicas/Factor-y-Desarrollo-Humano/Tecnicas_Didacticas.pdf","https://ciiasa.asa.gob.mx/calendarios/Calendario-SAFETY-2025.pdf"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCiiasaBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            try {
                val service = services

                binding.rvCiiasaCourse.apply {
                    layoutManager = GridLayoutManager(requireContext()
                        , 2)
                    adapter = CoursesAdapter(service,{ selectedCalendar ->
                        val uri = Uri.parse(selectedCalendar.calendar)
                        lifecycleScope.launch {
                            allowAllSSL()
                            val file = downloadFileToCache(requireContext(), uri.toString(), Constants.PDF_TEMP_FILE)
                            val action = file?.let {
                                CIIASAFragmentDirections.actionCIIASAFragmentToCiiasaPDFViewerFragment(
                                    it.absolutePath)
                            }
                            if (action != null) {
                                findNavController().navigate(action)
                            }else{
                                dialogOK(requireContext(),getString(R.string.err_title),getString(R.string.err_download))
                            }
                        }
                    }, { selectedCourse ->
                        val uri = Uri.parse(selectedCourse.course)
                        lifecycleScope.launch {
                            allowAllSSL()
                            val file = downloadFileToCache(requireContext(), uri.toString(), Constants.PDF_TEMP_FILE)
                            val action = file?.let {
                                CIIASAFragmentDirections.actionCIIASAFragmentToCiiasaPDFViewerFragment(
                                    it.absolutePath)
                            }
                            if (action != null) {
                                findNavController().navigate(action)
                            }else{
                                dialogOK(requireContext(),getString(R.string.err_title),getString(R.string.err_download))
                            }
                        }
                    })
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                binding.pbLoading.visibility = View.GONE
            }
        }

        binding.apply {
            btnRentRoom.setOnClickListener {
                val action = CIIASAFragmentDirections.actionCIIASAFragmentToServicesPDFViewerFragment(
                    Constants.SHOWRENTROOMCIIASA)
                findNavController().navigate(action)
            }
            btnLocation.setOnClickListener {
                val uri = Uri.parse(Constants.URL_MAPCIIASA)
                if (URLUtil.isValidUrl(uri.toString())) {
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
            }
            btnAboutCIIASA.setOnClickListener {
                val dialog = CIIASADialog()
                dialog.show(childFragmentManager,getString(R.string.serv_ciiasa))
            }
            btnWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_CIIASA))
                startActivity(intent)
            }
            btnPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_WHATS))
                startActivity(intent)
            }
        }




        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}