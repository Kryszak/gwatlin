package io.github.kryszak.gwatlin.api.guild.model.log

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
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
    val action: UpgradeAction,
    @SerialName("upgrade_id")
    val upgradeId: Int,
    @SerialName("recipe_id")
    val recipeId: Int? = null,
    val count: Int? = null
) : GuildLog {
    override val type: String by serialNameDelegate
}
