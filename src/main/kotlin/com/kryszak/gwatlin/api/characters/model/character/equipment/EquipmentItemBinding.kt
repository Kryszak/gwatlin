package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentItemBinding {
    @SerializedName("Account")
    ACCOUNT,
    @SerializedName("Character")
    CHARACTER
}