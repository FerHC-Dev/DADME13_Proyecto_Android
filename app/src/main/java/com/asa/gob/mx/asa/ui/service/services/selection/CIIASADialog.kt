package com.asa.gob.mx.asa.ui.service.services.selection

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.asa.gob.mx.asa.R
import com.asa.gob.mx.asa.databinding.CiiasaDialogBinding

class CIIASADialog(

):DialogFragment() {

    private var _binding: CiiasaDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialog: Dialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CiiasaDialogBinding.inflate(layoutInflater)
        super.onCreateDialog(savedInstanceState)

        dialog = buildOKDialog()

        return dialog
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun buildOKDialog(): Dialog =
        AlertDialog.Builder(requireContext()).setView(binding.root)
            .setTitle(getString(R.string.ciiasa_about))
            .setPositiveButton(getString(R.string.btn_close)){ _, _ ->
            }
            .create()

    private fun buildDialog(btnText1: String
                            , btnText2: String
                            ,positiveButton:() -> Unit
                            ,negativeButton:() -> Unit): Dialog =
        AlertDialog.Builder(requireContext()).setView(binding.root)
            .setTitle(getString(R.string.ciiasa_about))
            .setPositiveButton(btnText1){ _ , _ ->
                positiveButton()
            }
            .setNegativeButton(btnText2) { _, _ ->
                negativeButton()
            }
            .create()


}