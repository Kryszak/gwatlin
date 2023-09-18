package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type ComboField
 */
@Serializable
@SerialName("ComboField")
data class ComboField(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    @SerialName("field_type")
    val fieldType: ComboFieldType
) : Fact
