package com.kryszak.gwatlin.api.wvw.model.upgrade


import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw upgrade tiers property
 */
data class WvwUpgradeTier(
        val name: String,
        val upgrades: List<WvwUpgradeTierUpgrade>,
        @SerializedName("yaks_required") val yaksRequired: Int
)
