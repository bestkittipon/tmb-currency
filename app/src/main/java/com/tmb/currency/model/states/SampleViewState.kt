package com.tmb.currency.model.states

import com.tmb.currency.model.CharacterPresentation


internal data class SampleViewState(
    val error: Error?,
    val searchResults: List<CharacterPresentation>?
)