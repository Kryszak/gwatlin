package io.github.kryszak.gwatlin.api.wvw.model.upgrade

import kotlinx.serialization.Serializable

/**
 * Data model for wvw upgrade object
 */
@Serializable
data class WvwUpgrade(
        val id: Int,
        val tiers: List<WvwUpgradeTier>
)
