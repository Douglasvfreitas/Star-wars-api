package com.example.exercicio.infra.models.character

data class CharactersPresentation(
    val persons: List<com.example.exercicio.models.Person>,
    val next: String? = null,
    val previous: String? = null,
    val count: Int
)