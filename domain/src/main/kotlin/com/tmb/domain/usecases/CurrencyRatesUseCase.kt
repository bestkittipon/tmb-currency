package com.tmb.domain.usecases

import com.tmb.domain.model.CurrencyRates
import com.tmb.domain.model.CurrencyRatesRequest
import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

typealias CurrencyRatesBaseUseCase = BaseUseCase<CurrencyRatesRequest, Flow<CurrencyRates>>

class CurrencyRatesUseCase(
    private val currencyRepository: ICurrencyRepository
) : CurrencyRatesBaseUseCase {

    override suspend operator fun invoke(params: CurrencyRatesRequest) = currencyRepository.getRatesCurrency(params.key, params.currencyCode)

}