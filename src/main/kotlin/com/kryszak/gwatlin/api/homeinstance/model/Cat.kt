package com.kryszak.gwatlin.api.homeinstance.model

import kotlinx.serialization.Serializable

/**
 * Data model for Cat object
 */
@Serializable
data class Cat(
        val id: Int,
        val hint: String?
)