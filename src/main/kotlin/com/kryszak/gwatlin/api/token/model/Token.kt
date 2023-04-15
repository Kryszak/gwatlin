package com.kryszak.gwatlin.api.token.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for api token
 */
@Serializable
data class Token(
        val id: String,
        val name: String,
        val permissions: List<String>,
        val type: String?,
        @SerialName("expires_at") val expiresAt: String?,
        @SerialName("issued_at") val issuedAt: String?,
        val urls: List<String>?
)
