package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp ladder settings
 */
@Serializable
data class PvpLadderSettings(
        val name: String,
        val duration: Int? = null,
        val scoring: String,
        val tiers: List<SettingsTier>
)
