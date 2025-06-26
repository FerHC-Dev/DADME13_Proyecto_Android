package com.asa.gob.mx.asa.ui.service.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentNoticesBinding
import com.asa.gob.mx.asa.utils.Constants

class NoticesFragment : Fragment() {

    private var _binding: FragmentNoticesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoticesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = NoticesFragmentArgs.fromBundle(requireArguments())

        val notices = args.notices

        binding.apply {
            tvTitleNotice.text = notices.title
            tvNoticeContent.text = Constants.TEMP_TEXT
        }

    }
}