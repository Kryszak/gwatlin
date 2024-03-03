package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type Unblockable
 */
@Serializable
@SerialName("Unblockable")
data class Unblockable(
    override val text: String? = null,
    override val icon: String? = null,
    val value: Boolean,
) : Fact {
    override val type by serialNameDelegate
}
