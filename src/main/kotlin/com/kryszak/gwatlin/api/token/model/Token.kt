package com.kryszak.gwatlin.api.token.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for api token
 */
data class Token(
        val id: String,
        val name: String,
        val permissions: List<String>,
        val type: String?,
        @SerializedName("expires_at") val expiresAt: String?,
        @SerializedName("issued_at") val issuedAt: String?,
        val urls: List<String>?
)
