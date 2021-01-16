package com.tmb.domain.usecases


interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}

/*
interface BaseTwoUseCase<in Parameter, in Parameter2, out Result> {
    suspend operator fun invoke(params: Parameter, params2: Parameter2): Result
}*/
