package com.kryszak.gwatlin.api.wvw.model.upgrade

import kotlinx.serialization.Serializable

/**
 * Data model for wvw upgrade tier upgrade property
 */
@Serializable
data class WvwUpgradeTierUpgrade(
        val description: String,
        val icon: String,
        val name: String
)
