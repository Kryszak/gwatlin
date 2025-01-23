package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.NumberSerializer
import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.Number

/**
 * Data model for skill/trait facts with type Percent
 */
@Serializable
@SerialName("Percent")
data class Percent(
    override val text: String? = null,
    override val icon: String? = null,
    @Serializable(with = NumberSerializer::class)
    val percent: Number? = null,
    @Serializable(with = NumberSerializer::class)
    val value: Number? = null,
) : Fact {
    override val type by serialNameDelegate
}
