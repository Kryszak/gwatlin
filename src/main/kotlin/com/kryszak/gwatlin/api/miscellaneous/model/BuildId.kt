package com.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.Serializable

/**
 * Data model for application build id
 */
@Serializable
data class BuildId(
        val id: Int
)
