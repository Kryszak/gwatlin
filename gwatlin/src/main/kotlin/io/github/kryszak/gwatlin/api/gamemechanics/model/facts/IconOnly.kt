package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with only icon field (30008 - Cyclone)
 */
@Serializable
@SerialName("")
data class IconOnly(override val icon: String?) : Fact {
    override val type by serialNameDelegate
    override val text = null
}
