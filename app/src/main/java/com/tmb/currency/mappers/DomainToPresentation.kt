package com.tmb.currency.mappers

import com.tmb.domain.model.Character
import com.tmb.currency.commons.convertToInches
import com.tmb.currency.model.CharacterPresentation


internal fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        convertToInches(height),
        url
    )
}