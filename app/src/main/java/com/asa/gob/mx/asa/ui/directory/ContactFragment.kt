package com.asa.gob.mx.asa.ui.directory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asa.gob.mx.asa.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

}
