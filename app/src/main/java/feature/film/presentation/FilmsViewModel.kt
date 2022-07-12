package feature.film.presentation

import androidx.lifecycle.ViewModel
import feature.film.data.FilmsInfra
import feature.film.domain.models.FilmsPresentation
import feature.utils.StateMachine
import feature.utils.stateMachineFunction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

internal class FilmsViewModel : ViewModel() {

    private val service = FilmsInfra()

    fun retrieveMovies(): Flow<StateMachine<FilmsPresentation>> =
        stateMachineFunction(Dispatchers.Default) {
            service.listMovies()
        }
}