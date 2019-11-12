package com.kryszak.gwatlin.api.wvw.model.upgrade

/**
 * Data model for wvw upgrade object
 */
data class WvwUpgrade(
        val id: Int,
        val tiers: List<WvwUpgradeTier>
)
