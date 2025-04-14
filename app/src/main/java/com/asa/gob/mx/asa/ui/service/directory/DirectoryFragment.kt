package com.asa.gob.mx.asa.ui.service.directory

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentDirectoryBinding

class DirectoryFragment : Fragment() {

    private lateinit var binding: FragmentDirectoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDirectoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCall = binding.btnLlamar
        val btnSendEmail = binding.btnEnviarCorreo

        btnCall.setOnClickListener {
            val phoneNumber = binding.txtDNumero.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        btnSendEmail.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_nav_directory_to_nav_contact)
        }
    }
}
