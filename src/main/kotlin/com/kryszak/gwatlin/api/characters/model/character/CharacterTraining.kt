package com.kryszak.gwatlin.api.characters.model.character

import com.kryszak.gwatlin.api.gamemechanics.model.profession.Training

data class CharacterTraining(
    val training: List<Training>
)