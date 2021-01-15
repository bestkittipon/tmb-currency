package com.tmb.currency.model.states

data class LoadingState(
  val isLoading: Boolean,
  val labels: List<String> = listOf()
)
