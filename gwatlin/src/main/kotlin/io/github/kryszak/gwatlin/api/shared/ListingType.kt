package io.github.kryszak.gwatlin.api.shared

import kotlinx.serialization.SerialName

/**
 * Data model for wizard's vault listing type field
 */
enum class ListingType {
    @SerialName("Featured")
    FEATURED,

    @SerialName("Normal")
    NORMAL,

    @SerialName("Legacy")
    LEGACY
}