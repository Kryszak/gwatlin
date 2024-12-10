package io.github.kryszak.gwatlin.api.characters.model

import io.github.kryszak.gwatlin.api.characters.model.character.BuildTab
import io.github.kryszak.gwatlin.api.characters.model.character.Crafting
import io.github.kryszak.gwatlin.api.characters.model.character.Training
import io.github.kryszak.gwatlin.api.characters.model.character.WvwAbility
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for consolidated character infos
 */
@Serializable
data class Character(
    // Core
    val name: String,
    val race: String,
    val gender: String,
    val profession: String,
    val level: Int,
    val guild: String? = null,
    val age: Int,
    val created: String,
    @SerialName("last_modified")
    val lastModified: String? = null,
    val deaths: Int,
    val title: Int? = null,

    // Non-Core
    val flags: List<String> = listOf(),
    val crafting: List<Crafting> = listOf(),
    val backstory: List<String> = listOf(),
    @SerialName("wvw_abilities")
    val wvwAbilities: List<WvwAbility>? = listOf(),
    val training: List<Training> = listOf(),

    @SerialName("build_tabs_unlocked")
    val buildTabsUnlocked: Int? = null,
    @SerialName("active_build_tab")
    val activeBuildTab: Int? = null,
    @SerialName("build_tabs")
    val buildTabs: List<BuildTab> = listOf(),

    @SerialName("equipment_tabs_unlocked")
    val equipmentTabsUnlocked: Int? = null,
    @SerialName("active_equipment_tab")
    val activeEquipmentTab: Int? = null,
    val equipment: List<EquipmentItem> = listOf(),
    @SerialName("equipment_tabs")
    val equipmentTabs: List<EquipmentTab> = listOf()
)