package com.tmb.currency.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tmb.currency.commons.NetworkUtils
import com.tmb.currency.commons.versionFrom
import com.tmb.currency.dialog.LoadingOverlayDialog
import com.tmb.currency.viewmodel.BaseViewModel

internal open class BaseActivity : AppCompatActivity() {

    internal val loadingOverlay: LoadingOverlayDialog by lazy {
        LoadingOverlayDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatusBar()
    }

    private fun whiteStatusBar() {
        if (versionFrom(Build.VERSION_CODES.M)) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = getColor(android.R.color.white)
        }
    }

    protected fun onNetworkChange(block: (Boolean) -> Unit) {
        NetworkUtils.getNetworkStatus(this)
            .observe(this, Observer { isConnected ->
                block(isConnected)
            })
    }

    protected fun subscribeToViewModel(viewModel: BaseViewModel) {
        viewModel.loadingState.observe(this, Observer {
            when (it.isLoading) {
                true -> showLoading(*it.labels.toTypedArray())
                false -> dismissLoading()
            }
        })
    }

    fun showLoading(vararg labels: String) {
        loadingOverlay.show(labels.toList())
    }

    fun dismissLoading() {
        loadingOverlay.dismiss()
    }
}