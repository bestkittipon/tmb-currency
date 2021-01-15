package com.tmb.data_remote.repository

import com.tmb.data_remote.api.SampleApiService
import com.tmb.data_remote.meppers.toDomain
import com.tmb.domain.model.Character
import com.tmb.domain.repository.ISampleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleRepository(
    private val apiService: SampleApiService
) : ISampleRepository {
    override suspend fun searchCharacters(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.searchCharacters()
        val starWarsCharacters = mutableListOf<Character>()
        for (starWarsCharacter in searchResponse.results) {
            starWarsCharacters.add(starWarsCharacter.toDomain())
        }
        emit(starWarsCharacters)
    }

}