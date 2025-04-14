package com.asa.gob.mx.asa.ui.service.directory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.content.Intent
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)

        val name = binding.etNameContact
        val phone = binding.etPhoneContact
        val email = binding.etEmailContact
        val message = binding.etMessageContact
        val btnSend = binding.btnSendContact

        btnSend.setOnClickListener {
            if(verifyData(name, phone, email, message)){
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.contc_send)))
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.contc_subject))
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.contc_messageSend,
                                                                name.text.toString(),
                                                                email.text.toString(),
                                                                phone.text.toString(),
                                                                message.text.toString()))
                startActivity(Intent.createChooser(intent, getString(R.string.contc_btnSend)))
            }else{
                Toast.makeText(requireContext(), getString(R.string.err_required), Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    private fun verifyData(name: EditText, phone: EditText, email: EditText, message: EditText): Boolean {
        return isNotEmptyData(name) && isValidPhone(phone) && isEmailValid(email) && isNotEmptyData(message)
    }

    private fun isEmailValid(email: EditText): Boolean {
        if(isNotEmptyData(email)){
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error = getString(R.string.err_email)
                email.requestFocus()
                return false
            }
        }else{
            return false
        }
        return true
    }

    private fun isNotEmptyData(campo : EditText): Boolean {
        if(campo.text.toString().isEmpty() || campo.text.toString().isBlank()){
            campo.error = getString(R.string.err_empty)
            campo.requestFocus()
            return false
        }
        return true
    }

    private fun isValidPhone(phone: EditText): Boolean {
        if(isNotEmptyData(phone)){
            if(!android.util.Patterns.PHONE.matcher(phone.text.toString()).matches()
                || phone.text.toString().length < 10){
                phone.error = getString(R.string.err_phone)
                phone.requestFocus()
                return false
            }
        }else{
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
