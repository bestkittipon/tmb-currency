package com.tmb.currency.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tmb.currency.R
import com.tmb.currency.databinding.DialogCurrencyRatesPickerBinding
import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyRatesInfoPresentation
import com.tmb.currency.model.CurrencyRatesPresentation
import com.tmb.currency.viewmodel.CurrencyRatesDialogViewModel
import com.tmb.domain.model.CurrencyRatesInfo

class CurrencyRatesDialog: DialogFragment() {

    private lateinit var currencyRates: List<CurrencyRatesInfo>
    private lateinit var listener: (CurrencyRatesInfoPresentation) -> Unit
    private lateinit var dialogBinding: DialogCurrencyRatesPickerBinding

    val viewModel: CurrencyRatesDialogViewModel by viewModels()

    companion object {
        fun newInstance(currencyRatesPresentation: CurrencyRatesPresentation, currencyInfoPresentation: CurrencyInfoPresentation) = CurrencyRatesDialog().apply {
            arguments = bundleOf(
                "currencyRatesPresentation" to currencyRatesPresentation,
                "currencyInfoPresentation" to currencyInfoPresentation
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_currency_rates_picker, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.setLayout(width, height)
        }
    }

    fun setAccountInfo(currencyRateList: List<CurrencyRatesInfo>) {
        this.currencyRates = currencyRateList
    }

    fun setOnAccountSelectListener(listener: (CurrencyRatesInfoPresentation) -> Unit) {
        this.listener = listener
    }

    private fun onAccountSelected(position: Int) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogBinding = DialogCurrencyRatesPickerBinding.bind(view)
        dialogBinding.viewModel = viewModel

        arguments?.getParcelable<CurrencyRatesPresentation>("currencyRatesPresentation")?.let { currencyRatesPresentation ->
            arguments?.getParcelable<CurrencyInfoPresentation>("currencyInfoPresentation")?.let { currencyInfoPresentation ->
                viewModel.init(currencyRatesPresentation, currencyInfoPresentation)
            }
        }

        viewModel.selectedCurrencyRate.observe(this.viewLifecycleOwner ,Observer { currencyRate ->
            this.listener.invoke(currencyRate)
            this.dismiss()
        })
    }
}
