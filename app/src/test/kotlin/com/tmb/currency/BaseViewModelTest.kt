package com.tmb.currency

import com.tmb.currency.utils.CoroutineTestRule
import com.tmb.currency.utils.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest


abstract class BaseViewModelTest : AutoCloseKoinTest() {
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    abstract fun prepareViewModel(uiState: UiState)
}