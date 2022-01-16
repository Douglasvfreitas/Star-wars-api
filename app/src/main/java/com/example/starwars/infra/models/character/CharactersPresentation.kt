package com.example.starwars.infra.models.character

import com.example.starwars.models.Character

data class CharactersPresentation(
    val characters: List<Character>,
    val next: String? = null,
    val previous: String? = null,
    val count: Int
)