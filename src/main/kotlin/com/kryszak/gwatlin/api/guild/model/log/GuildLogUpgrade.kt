package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "upgrade"
 */
@Serializable
@SerialName("upgrade")
data class GuildLogUpgrade(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    override val type: String,
    val action: UpgradeAction,
    @SerialName("upgrade_id")
    val upgradeId: Int,
    @SerialName("recipe_id")
    val recipeId: Int? = null,
    val count: Int? = null
) : GuildLog
