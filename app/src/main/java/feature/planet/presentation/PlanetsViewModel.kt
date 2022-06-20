package feature.planet.presentation

import androidx.lifecycle.ViewModel
import feature.planet.data.PlanetInfra
import feature.planet.domain.models.PlanetsPresentation
import feature.utils.StateMachine
import feature.utils.stateMachineFunction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

internal class PlanetsViewModel : ViewModel() {

    private val service = PlanetInfra()

    fun retrievePresentation(): Flow<StateMachine<PlanetsPresentation>> =
        stateMachineFunction(Dispatchers.Default) {
            service.listPlanet()
        }
}