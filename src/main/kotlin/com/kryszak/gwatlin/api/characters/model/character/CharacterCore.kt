package com.kryszak.gwatlin.api.characters.model.character

import com.google.gson.annotations.SerializedName

data class CharacterCore(
    val name: String,
    val race: String,
    val gender: String,
    val profession: String,
    val level: Int,
    val guild: String,
    val age: Int,
    val created: String,
    @SerializedName("last_modified")
    val lastModified: String,
    val deaths: Int,
    val title: Int
)
