package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type ComboField
 */
@Serializable
@SerialName("ComboField")
data class ComboField(
    override val text: String? = null,
    override val icon: String? = null,
    @SerialName("field_type")
    val fieldType: ComboFieldType
) : Fact {
    override val type by serialNameDelegate
}
