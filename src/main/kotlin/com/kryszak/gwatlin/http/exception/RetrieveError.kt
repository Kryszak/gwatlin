package com.kryszak.gwatlin.http.exception

import kotlinx.serialization.Serializable

@Serializable
internal data class RetrieveError(val text: String)
