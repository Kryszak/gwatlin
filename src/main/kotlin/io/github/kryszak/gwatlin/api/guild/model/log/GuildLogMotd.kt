package io.github.kryszak.gwatlin.api.guild.model.log

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "motd"
 */
@Serializable
@SerialName("motd")
data class GuildLogMotd(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    val motd: String
) : GuildLog {
    override val type: String by serialNameDelegate
}
