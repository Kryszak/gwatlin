package com.kryszak.gwatlin.api.pvp.model.season

/**
 * Data model for pvp ladder settings
 */
data class PvpLadderSettings(
        val name: String,
        val duration: Int,
        val scoring: String,
        val tiers: List<SettingsTier>
)
