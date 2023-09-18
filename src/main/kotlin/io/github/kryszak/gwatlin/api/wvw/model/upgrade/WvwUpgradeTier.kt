package io.github.kryszak.gwatlin.api.wvw.model.upgrade


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw upgrade tiers property
 */
@Serializable
data class WvwUpgradeTier(
        val name: String,
        val upgrades: List<WvwUpgradeTierUpgrade>,
        @SerialName("yaks_required") val yaksRequired: Int
)
