package com.tmb.data_remote.helpers
import com.tmb.data_remote.helpers.Constants.ACCESS_KEY
import com.tmb.data_remote.helpers.Constants.SOURCE
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

internal class CurrencyRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/live?access_key=$ACCESS_KEY&source=$SOURCE" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/rates.json"))
            }
            "/list?access_key=$ACCESS_KEY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/list.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}