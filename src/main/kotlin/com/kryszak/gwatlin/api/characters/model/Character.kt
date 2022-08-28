package com.kryszak.gwatlin.api.characters.model

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.characters.model.character.*
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab

data class Character(
    // Core
    val name: String,
    val race: String,
    val gender: String,
    val flags: List<String>,
    val profession: String,
    val level: Int,
    val guild: String,
    val age: Int,
    val created: String,
    @SerializedName("last_modified")
    val lastModified: String,
    val deaths: Int,
    val title: Int,

    // Non-Core
    val crafting: List<Crafting>,
    val backstory: List<String>,
    @SerializedName("wvw_abilities")
    val wvwAbilities: List<WvwAbility>,
    val training: List<Training>,

    @SerializedName("build_tabs_unlocked")
    val buildTabsUnlocked: Int,
    @SerializedName("active_build_tab")
    val activeBuildTab: Int,
    @SerializedName("build_tabs")
    val buildTabs: List<BuildTab>,

    @SerializedName("equipment_tabs_unlocked")
    val equipmentTabsUnlocked: Int,
    @SerializedName("active_equipment_tab")
    val activeEquipmentTab: Int,
    val equipment: List<EquipmentItem>,
    @SerializedName("equipment_tabs")
    val equipmentTabs: List<EquipmentTab>
)