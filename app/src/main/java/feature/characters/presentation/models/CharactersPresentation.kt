package feature.characters.presentation.models

import feature.characters.domain.models.Character

data class CharactersPresentation(
    val characters: List<Character>,
    val next: String? = null,
    val previous: String? = null,
    val count: Int
)