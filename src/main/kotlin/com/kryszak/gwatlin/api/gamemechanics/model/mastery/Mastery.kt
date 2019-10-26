package com.kryszak.gwatlin.api.gamemechanics.model.mastery

/**
 * Data model for Mastery object
 */
data class Mastery(
        val id: Int,
        val name: String,
        val requirement: String,
        val order: Int,
        val background: String,
        val region: String,
        val levels: List<MasteryLevel>
)