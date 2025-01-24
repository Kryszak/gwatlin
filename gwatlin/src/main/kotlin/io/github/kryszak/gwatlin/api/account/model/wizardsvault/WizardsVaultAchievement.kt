package io.github.kryszak.gwatlin.api.account.model.wizardsvault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wizard's vault achievement (daily and weekly)
 */
@Serializable
data class WizardsVaultAchievement(
    @SerialName("meta_progress_current")
    val metaProgressCurrent: Int,
    @SerialName("meta_progress_complete")
    val metaProgressComplete: Int,
    @SerialName("meta_reward_item_id")
    val metaRewardItemId: Int,
    @SerialName("meta_reward_astral")
    val metaRewardAstral: Int,
    @SerialName("meta_reward_claimed")
    val metaRewardClaimed: Boolean,
    val objectives: List<WizardsVaultObjective> = listOf(),
)

/**
 * Data model for wizard's vault objective (special, daily and weekly)
 */
@Serializable
data class WizardsVaultObjective(
    val id: Int,
    val title: String,
    val track: String,
    val acclaim: Int,
    @SerialName("progress_current")
    val progressCurrent: Int,
    @SerialName("progress_complete")
    val progressComplete: Int,
    val claimed: Boolean
)

/**
 * Data model for wizard's vault special objectives
 */
@Serializable
data class WizardsVaultSpecialObjectives(
    val objectives: List<WizardsVaultObjective> = listOf(),
)