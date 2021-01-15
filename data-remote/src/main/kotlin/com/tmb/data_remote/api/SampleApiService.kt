package com.tmb.data_remote.api

import com.tmb.data_remote.models.SearchResponse
import com.tmb.data_remote.models.SpecieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface SampleApiService {

    @GET("people/")
    suspend fun searchCharacters(): SearchResponse

    @GET
    suspend fun getSpecieDetails(@Url speciesUrl: String): SpecieDetailResponse
}