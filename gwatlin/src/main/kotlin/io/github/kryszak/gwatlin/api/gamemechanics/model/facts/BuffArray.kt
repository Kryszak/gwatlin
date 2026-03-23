package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BuffArray")
data class BuffArray(
    override val icon: String,
) : Fact {
    override val type by serialNameDelegate

    override val text = null
}
