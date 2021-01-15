package com.tmb.domain.usecases

import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

typealias CurrencyBaseUseCase = BaseUseCase<String, Flow<Map<String, String>>>

class CurrencyUseCase(
    private val currencyRepository: ICurrencyRepository
) : CurrencyBaseUseCase {

    override suspend operator fun invoke(params: String) = currencyRepository.getCurrency(params)

}