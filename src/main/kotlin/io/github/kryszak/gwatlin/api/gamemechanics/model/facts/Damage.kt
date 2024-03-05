package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type Damage
 */
@Serializable
@SerialName("Damage")
data class Damage(
    override val text: String? = null,
    override val icon: String? = null,
    @SerialName("hit_count")
    val hitCount: Int,
    @SerialName("dmg_multiplier")
    val dmgMultiplier: Float? = null,
) : Fact {
    override val type by serialNameDelegate

    /**
     *
     */
    @Deprecated(
        message = "Most likely this field was a typo.",
        replaceWith = ReplaceWith("dmgMultiplier"),
        level = DeprecationLevel.WARNING,
    )
    val finisherType: Float? = dmgMultiplier
}
