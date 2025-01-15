package io.github.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for achievement object
 */
@Serializable
data class SchemaVersion(
    @SerialName("v") val version: String,
    @SerialName("desc") val description: String
)