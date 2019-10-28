package com.kryszak.gwatlin.api.gamemechanics.model.specialization

import com.google.gson.annotations.SerializedName

/**
 * Data model for specialization object
 */
data class Specialization(
        val id: Int,
        val name: String,
        val profession: String,
        val elite: Boolean,
        val icon: String,
        val background: String,
        @SerializedName("minor_traits") val minorTraits: List<Int>,
        @SerializedName("major_traits") val majorTraits: List<Int>
)