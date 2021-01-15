package com.tmb.data_remote.meppers

import com.tmb.data_remote.models.CharacterResponse
import com.tmb.domain.model.Character


internal fun CharacterResponse.toDomain(): Character {
    return Character(this.name, this.birthYear, this.height, this.url)
}