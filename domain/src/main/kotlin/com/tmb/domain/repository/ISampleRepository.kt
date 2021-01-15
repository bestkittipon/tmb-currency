package com.tmb.domain.repository

import com.tmb.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ISampleRepository {
    suspend fun searchCharacters(characterName: String): Flow<List<Character>>
}