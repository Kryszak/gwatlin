package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type Number
 */
@Serializable
@SerialName("Number")
data class Number(
    override val text: String? = null,
    override val icon: String? = null,
    val value: Int,
) : Fact {
    override val type by serialNameDelegate
}
