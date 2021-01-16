package com.tmb.domain.usecases

import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

typealias CurrencyRatesBaseUseCase = BaseUseCase<String, Flow<Map<String, Double>>>

class CurrencyRatesUseCase(
    private val currencyRepository: ICurrencyRepository
) : CurrencyRatesBaseUseCase {

    override suspend operator fun invoke(params: String) = currencyRepository.getRatesCurrency(params, params)

}