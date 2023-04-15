package com.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Serializable

/**
 * Data model for pvp ladder settings
 */
@Serializable
data class PvpLadderSettings(
        val name: String,
        val duration: Int,
        val scoring: String,
        val tiers: List<SettingsTier>
)
