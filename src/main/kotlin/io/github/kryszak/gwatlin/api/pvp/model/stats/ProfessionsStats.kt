package io.github.kryszak.gwatlin.api.pvp.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for profession stats
 */
@Serializable
data class ProfessionsStats(
        val guardian: WinLossStats? = null,
        val warrior: WinLossStats? = null,
        val engineer: WinLossStats? = null,
        val ranger: WinLossStats? = null,
        val thief: WinLossStats? = null,
        val elementalist: WinLossStats? = null,
        val mesmer: WinLossStats? = null,
        val necromancer: WinLossStats? = null,
        val revenant: WinLossStats? = null
)
