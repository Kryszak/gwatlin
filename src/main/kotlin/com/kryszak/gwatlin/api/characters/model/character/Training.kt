package com.kryszak.gwatlin.api.characters.model.character

/**
 * Data model for training infos
 */
data class Training(
    val id: Int,
    val spent: Int,
    val done: Boolean
)