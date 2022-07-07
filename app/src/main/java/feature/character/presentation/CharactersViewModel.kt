package feature.character.presentation

import androidx.lifecycle.ViewModel
import feature.character.data.CharacterInfra
import feature.character.domain.models.CharactersPresentation
import feature.utils.StateMachine
import feature.utils.stateMachineFunction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

internal class CharactersViewModel : ViewModel() {

    private val service = CharacterInfra()

    fun retrievePresentation(): Flow<StateMachine<CharactersPresentation>> =
        stateMachineFunction(Dispatchers.Default) {
            service.listCharacter()
        }
}