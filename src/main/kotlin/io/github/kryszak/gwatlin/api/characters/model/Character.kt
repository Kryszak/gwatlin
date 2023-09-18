package io.github.kryszak.gwatlin.api.characters.model

import kotlinx.serialization.SerialName
import io.github.kryszak.gwatlin.api.characters.model.character.*
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
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
    val guild: String,
    val age: Int,
    val created: String,
    @SerialName("last_modified")
    val lastModified: String,
    val deaths: Int,
    val title: Int,

    // Non-Core
    val flags: List<String>,
    val crafting: List<Crafting>,
    val backstory: List<String>,
    @SerialName("wvw_abilities")
    val wvwAbilities: List<WvwAbility>,
    val training: List<Training>,

    @SerialName("build_tabs_unlocked")
    val buildTabsUnlocked: Int,
    @SerialName("active_build_tab")
    val activeBuildTab: Int,
    @SerialName("build_tabs")
    val buildTabs: List<BuildTab>,

    @SerialName("equipment_tabs_unlocked")
    val equipmentTabsUnlocked: Int,
    @SerialName("active_equipment_tab")
    val activeEquipmentTab: Int,
    val equipment: List<EquipmentItem>,
    @SerialName("equipment_tabs")
    val equipmentTabs: List<EquipmentTab>
)