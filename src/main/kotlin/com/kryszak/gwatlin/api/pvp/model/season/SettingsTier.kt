package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for settings tier object
 */
@Serializable
data class SettingsTier(
    val range: List<Double>
)
