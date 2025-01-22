package io.github.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for api token
 */
@Serializable
data class Token(
        val id: String,
        val name: String,
        val permissions: List<String> = listOf(),
        val type: String? = null,
        @SerialName("expires_at") val expiresAt: String? = null,
        @SerialName("issued_at") val issuedAt: String? = null,
        val urls: List<String> = listOf()
)
