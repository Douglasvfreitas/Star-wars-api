package feature.character.domain.models

data class CharactersPresentation(
    val characters: List<Character>,
    val next: String? = null,
    val previous: String? = null,
    val count: Int
)