package io.github.kryszak.gwatlin.api.guild.model.log

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "joined"
 */
@Serializable
@SerialName("joined")
data class GuildLogJoined(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
) : GuildLog {
    override val type: String by serialNameDelegate
}
