package io.github.kryszak.gwatlin.api.wizardsvault.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Data model for Wizard's Vault season response
 */
@Serializable
data class WizardsVaultSeason(
    val title: String,
    @Contextual
    val start: OffsetDateTime,
    @Contextual
    val end: OffsetDateTime,
    val listings: List<Int>,
    val objectives: List<Int>
)
