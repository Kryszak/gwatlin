package io.github.kryszak.gwatlin.http.exception

import com.github.kittinunf.fuel.core.Response
import kotlinx.serialization.DeserializationStrategy

internal data class ErrorResponse<T>(val response: Response, val deserializationStrategy: DeserializationStrategy<T>)