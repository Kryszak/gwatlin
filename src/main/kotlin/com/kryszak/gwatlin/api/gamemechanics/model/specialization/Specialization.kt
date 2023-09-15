package com.kryszak.gwatlin.api.gamemechanics.model.specialization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for specialization object
 */
@Serializable
data class Specialization(
        val id: Int,
        val name: String,
        val profession: String,
        val elite: Boolean,
        val icon: String,
        val background: String,
        @SerialName("minor_traits") val minorTraits: List<Int>,
        @SerialName("major_traits") val majorTraits: List<Int>
)