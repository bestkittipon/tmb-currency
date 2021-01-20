package com.tmb.data_remote


import com.tmb.data_remote.helpers.CurrencyRequestDispatcher
import com.tmb.data_remote.api.CurrencyApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


internal open class BaseTest {

    private lateinit var mockWebServer: MockWebServer

    lateinit var currencyApiService: CurrencyApiService

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = CurrencyRequestDispatcher()
        mockWebServer.start()
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient = buildOkhttpClient(loggingInterceptor)

        currencyApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CurrencyApiService::class.java)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}