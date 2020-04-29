package com.junka.jdialog.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.junka.jdialog.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_one.setOnClickListener{

            val customDialog = CustomDialog.Builder()
                .setTitleText("New Title")
                .setDescriptionText("Nueva Descripcion")
                .setPositiveButtonText("Aceptar")
                .setNegativeButtonText("Cancelar")
                .buid()

            customDialog.show(parentFragmentManager,javaClass.name)
            customDialog.setDialogClickListener(object : CustomDialog.IDialogButtonClickListener{
                override fun onPositiveClickListener() {
                    Toast.makeText(requireActivity(),"Dialog 1 OK",Toast.LENGTH_SHORT).show()
                }

                override fun onNegativeClickListener() {
                    customDialog.dismiss()
                    Toast.makeText(requireActivity(),"Dialog 1 Cancel",Toast.LENGTH_SHORT).show()
                }
            })
        }

        dialog_two.setOnClickListener {
            val customDialog = CustomDialog.Builder()
                .setTitleText(" Desea guardar esta información ?")
                .setDescriptionText("Se guardara la informacion en su telefono")
                .setPositiveButtonText("Ok")
                .setNegativeButtonText("No")
                .isNegativeButtonEnabled(false)
                .buid()

            customDialog.show(parentFragmentManager,javaClass.name)
            customDialog.setDialogClickListener(object : CustomDialog.IDialogButtonClickListener{
                override fun onPositiveClickListener() {
                    Toast.makeText(requireActivity(),"Dialog 2 Ok",Toast.LENGTH_SHORT).show()
                }

                override fun onNegativeClickListener() {
                    customDialog.dismiss()
                    Toast.makeText(requireActivity(),"Dialog 2 Cancel",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}
