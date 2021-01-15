package com.tmb.currency

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.tmb.currency.base.DataBindingActivity
import com.tmb.currency.databinding.ActivityMainBinding
import com.tmb.currency.viewmodel.SampleViewModel


internal class MainActivity : DataBindingActivity<ActivityMainBinding, SampleViewModel>(SampleViewModel::class) {

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
    }

    override fun initialView() {
        viewModel.executeCharacterSearch("")
    }

    override fun observeViewModel() {
        viewModel.searchViewState.observe(this, Observer { state ->
            state.searchResults?.let { searchResults ->
                if (searchResults.isEmpty()) {
                    //handleNoSearchResults()
                    return@let
                }
                //handleSearchResults(searchResults)
            }
        })
    }
}
