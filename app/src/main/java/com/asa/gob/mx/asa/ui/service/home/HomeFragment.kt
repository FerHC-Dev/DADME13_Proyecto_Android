package com.asa.gob.mx.asa.ui.service.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.asa.gob.mx.asa.application.NoticeRFApp
import com.asa.gob.mx.asa.data.NoticeRepository
import com.asa.gob.mx.asa.data.remote.model.NoticeDto
import com.asa.gob.mx.asa.data.remote.model.Notices
import com.asa.gob.mx.asa.databinding.FragmentHomeBinding
import com.asa.gob.mx.asa.ui.adapters.NoticeAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())
    private var carruselPosition = 0
    private lateinit var runnable: Runnable

    private var notices: List<NoticeDto> = listOf(
        NoticeDto("1","Notice1","","12/12/2023"),
        NoticeDto("2","Notice2","","12/12/2024"),
        NoticeDto("3","Notice3","","12/12/2024"),
        NoticeDto("4","Notice4","","12/12/2025"),
    )

    private lateinit var repository: NoticeRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //repository = (requireActivity().application as NoticeRFApp).repository

        lifecycleScope.launch {
            try{
                //val notice = repository.getNotices()
                val notice = notices

                binding.rvNotice.apply {
                    layoutManager = LinearLayoutManager(requireContext()
                        , LinearLayoutManager.HORIZONTAL, false)
                    adapter = NoticeAdapter(notice){ selectedNotice ->
                        val action = HomeFragmentDirections.actionNavHomeToNoticesFragment(
                            Notices(selectedNotice.id?:""
                                ,selectedNotice.title?:""
                                ,selectedNotice.image?:"")
                        )
                        findNavController().navigate(action)
                    }
                }

            }catch (e: Exception){
             e.printStackTrace()
            }finally {
                binding.pbLoading.visibility = View.GONE
            }
        }

        // Para centrar los ítems como carrusel
        //val snapHelper = LinearSnapHelper()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvNotice)
        cycleNotice(binding.rvNotice)

    }

    private fun cycleNotice(rv: RecyclerView, duration: Long = 3000){
        runnable = object : Runnable {
            override fun run() {
                if (rv.adapter == null) return

                val itemCount = rv.adapter!!.itemCount

                if (itemCount == 0) return

                if (carruselPosition >= itemCount) {
                    carruselPosition = 0
                }

                rv.smoothScrollToPosition(carruselPosition)
                carruselPosition++
                handler.postDelayed(this, duration) // cada 3 segundos
            }
        }
        handler.postDelayed(runnable, duration)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        handler.removeCallbacks(runnable)
    }


}