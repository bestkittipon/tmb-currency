package com.tmb.currency.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.tmb.currency.R
import kotlinx.android.synthetic.main.dialog_message.view.*

class MessageDialog : DialogFragment() {

    private var callBack: (() -> Unit)? = null

    companion object {
        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"

        fun newDialog(
            title: String,
            message: String
        ) = MessageDialog()
            .apply {
                arguments = bundleOf(
                    KEY_TITLE to title,
                    KEY_MESSAGE to message
                )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_message, container, false)

        val title = arguments?.getString(KEY_TITLE) ?: ""
        val message = arguments?.getString(KEY_MESSAGE) ?: ""

        view.txtTitle.text = title
        view.txtMessage.text = message
        view.btnOK.setOnClickListener {
            callBack?.invoke()
            dismiss()
        }

        return view
    }

    fun setCallBackListener(callBack: (() -> Unit)?) {
        this.callBack = callBack
    }
}

