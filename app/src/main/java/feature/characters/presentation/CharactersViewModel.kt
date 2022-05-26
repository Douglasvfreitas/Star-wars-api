package feature.characters.presentation

import androidx.lifecycle.ViewModel
import com.example.starwars.infra.models.character.CharactersPresentation
import feature.characters.data.CharactersInfra
import feature.utils.StateMachine
import feature.utils.stateMachineFunction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

internal class CharactersViewModel : ViewModel() {

    private val service = CharactersInfra()
    private val dispatcher = Dispatchers.Default

    fun retrievePresentation(): Flow<StateMachine<CharactersPresentation>> =
        stateMachineFunction(dispatcher) {
            service.listCharacters()
        }
}