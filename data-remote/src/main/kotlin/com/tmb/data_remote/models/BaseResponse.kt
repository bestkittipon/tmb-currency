package com.tmb.data_remote.models

import com.squareup.moshi.Json

open class BaseResponse {
    @field:Json(name = "success") var valid: Boolean = false
    @field:Json(name = "error") var error: ErrorResponse? = null
}

class ErrorResponse {
    @field:Json(name = "code") var code: Int? = null
    @field:Json(name = "info") var message: String? = null
}