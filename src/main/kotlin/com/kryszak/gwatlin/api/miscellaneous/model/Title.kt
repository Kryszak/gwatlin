package com.kryszak.gwatlin.api.miscellaneous.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for title object
 */
data class Title(
        val id: Int,
        val name: String,
        val achievement: Int,
        val achievements: List<Int>,
        @SerializedName("ap_required") val apRequired: Int?
)
