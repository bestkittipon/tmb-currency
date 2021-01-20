package com.tmb.data_remote.repository

import com.google.common.truth.Truth
import com.tmb.data_remote.BaseTest
import com.tmb.data_remote.helpers.Constants.ACCESS_KEY
import com.tmb.data_remote.helpers.Constants.SOURCE
import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CurrencyRepositoryTest : BaseTest() {

    private lateinit var currencyRepository: ICurrencyRepository

    @Before
    override fun setup() {
        super.setup()
        currencyRepository = CurrencyRepository(currencyApiService)
    }

    @Test
    fun `given existing list when executed then return list of currency results`() {
        runBlocking {
            val result = currencyRepository.getCurrency(ACCESS_KEY)
            result.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it.valid)
                Truth.assertThat(it.currencies).hasSize(168)
            }
        }
    }

    @Test
    fun `given existing list when executed then return list of rates currency results`() {
        runBlocking {
            val result = currencyRepository.getRatesCurrency(ACCESS_KEY, SOURCE)
            result.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it.valid)
                Truth.assertThat(it.error?.message?.isEmpty())
            }
        }
    }
}