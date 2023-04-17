package com.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.Serializable

/**
 * Data model for quaggan objects
 */
@Serializable
data class Quaggan(
    val id: String,
    val url: String
)
