package com.junka.jdialog.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.junka.jdialog.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog private constructor(
    private val titleText: String?,
    private val descriptionText: String?,
    private val positiveButtonText: String?,
    private val negativeButtonText: String?,
    private val isNegativeButtonVisible : Boolean,
    private var dialogClickListener : IDialogButtonClickListener? = null
) : DialogFragment()  {

    interface IDialogButtonClickListener {
        fun onPositiveClickListener()
        fun onNegativeClickListener()
    }

    data class Builder(
        private var titleText: String? = null,
        private var descriptionText: String? = null,
        private var positiveButtonText: String? = null,
        private var negativeButtonText: String? = null,
        private var isNegativeButtonVisible : Boolean = true
    ) {

        fun setTitleText(titleText: String?) = apply { this.titleText = titleText }
        fun setDescriptionText(descriptionText: String?) = apply { this.descriptionText = descriptionText }
        fun setPositiveButtonText(positiveButtonText: String?) = apply { this.positiveButtonText = positiveButtonText }
        fun setNegativeButtonText(negativeButtonText: String?) = apply { this.negativeButtonText = negativeButtonText }
        fun isNegativeButtonEnabled(enabled:Boolean) = apply { this.isNegativeButtonVisible = enabled }

        fun buid() = CustomDialog(titleText,descriptionText,positiveButtonText,negativeButtonText,isNegativeButtonVisible)
    }

    fun setDialogClickListener(listener: IDialogButtonClickListener?) = apply { this.dialogClickListener = listener }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog_title.text = titleText
        dialog_description.text = descriptionText
        dialog_ok.text = positiveButtonText
        dialog_cancel.text = negativeButtonText

        if(!isNegativeButtonVisible)
            dialog_cancel.visibility =View.GONE

        dialog_ok.setOnClickListener{
            dialogClickListener?.onPositiveClickListener()
        }

        dialog_cancel.setOnClickListener{
            dialogClickListener?.onNegativeClickListener()
        }

    }
}