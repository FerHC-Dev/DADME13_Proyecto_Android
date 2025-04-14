package com.asa.gob.mx.asa.ui.service.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentAboutBinding
import com.asa.gob.mx.asa.utils.Constants

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        binding.footer.apply {
            btnPrivacy.setOnClickListener {
                findNavController().navigate(R.id.action_nav_about_to_privacyFragment)
            }
            btnTerms.setOnClickListener {
                findNavController().navigate(R.id.action_nav_about_to_termsFragment)
            }
        }

        binding.btnMoreInformation.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.URL_GOB))
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}