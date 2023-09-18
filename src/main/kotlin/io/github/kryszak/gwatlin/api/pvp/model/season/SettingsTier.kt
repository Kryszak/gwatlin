package io.github.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for settings tier object
 */
@Serializable
data class SettingsTier(
    val range: List<Double>,
    val color: String? = null,
    val type: String? = null,
    val name: String? = null
)
