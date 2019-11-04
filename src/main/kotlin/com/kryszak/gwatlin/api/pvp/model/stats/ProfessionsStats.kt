package com.kryszak.gwatlin.api.pvp.model.stats

/**
 * Data model for profession stats
 */
data class ProfessionsStats(
        val guardian: WinLossStats?,
        val warrior: WinLossStats?,
        val engineer: WinLossStats?,
        val ranger: WinLossStats?,
        val thief: WinLossStats?,
        val elementalist: WinLossStats?,
        val mesmer: WinLossStats?,
        val necromancer: WinLossStats?,
        val revenant: WinLossStats?
)
