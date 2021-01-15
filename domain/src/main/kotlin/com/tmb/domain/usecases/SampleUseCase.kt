package com.tmb.domain.usecases

import com.tmb.domain.model.Character
import com.tmb.domain.repository.ISampleRepository
import kotlinx.coroutines.flow.Flow

typealias SampleBaseUseCase = BaseUseCase<String, Flow<List<Character>>>

class SampleUseCase(
    private val sampleRepository: ISampleRepository
) : SampleBaseUseCase {

    override suspend operator fun invoke(params: String) = sampleRepository.searchCharacters(params)

}