package com.kryszak.gwatlin.api.characters.model.character

import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem

data class CharacterEquipment(
    val equipment: List<EquipmentItem>
)