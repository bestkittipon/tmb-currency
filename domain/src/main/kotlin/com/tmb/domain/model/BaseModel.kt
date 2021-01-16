package com.tmb.domain.model

open class BaseModel {
    var valid: Boolean = false
    var error: Error? = null
}

class Error {
    var code: Int? = null
    var message: String? = null
}