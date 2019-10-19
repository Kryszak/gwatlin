package com.kryszak.gwatlin.http.exception

import com.github.kittinunf.fuel.core.Response

internal data class ErrorResponse<T>(val response: Response, val responseType: Class<T>)