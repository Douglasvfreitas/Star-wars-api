package feature.planet.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.starwars.R
import feature.Params
import feature.planet.data.models.PlanetDetailsResponse
import feature.planet.domain.models.PlanetsPresentation
import feature.planet.presentation.adapter.PlanetAdapter
import feature.utils.StateMachine
import feature.utils.collectIn
import kotlinx.android.synthetic.main.activity_films.*

class PlanetsActivity : AppCompatActivity() {
    private val viewModel by lazy { PlanetsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        handleStates()
        listenButton()
    }

    private fun listenButton() {
        retryButton.setOnClickListener {
            handleStates()
        }
    }

    private fun handleStates() {
        viewModel.retrievePresentation().collectIn(lifecycleScope) { event ->
            when (event) {
                StateMachine.Start -> handleLoading(true)
                is StateMachine.Success -> handleResult(event.value)
                is StateMachine.Failure -> handleError()
                StateMachine.Finish -> handleLoading(false)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        loadingState.isVisible = isLoading
        filmsRv.isVisible = !isLoading
        retryButton.isVisible = false
    }

    private fun handleResult(presentation: PlanetsPresentation) {
        handleLoading(false)
        filmsRv.adapter = PlanetAdapter(
            planets = presentation.planets, navigateToPlanetDetails = { planet ->
                moveToPlanetDetails(planet)
            }
        )
    }

    private fun handleError() {
        Toast.makeText(this, ERROR_MESSAGE, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
        handleLoading(false)
        retryButton.isVisible = true
    }

    private fun moveToPlanetDetails(planets: PlanetDetailsResponse) {
        startActivity(Intent(this, PlanetsDetailsActivity::class.java).apply {
            putExtra(Params.PLANET_FILM, planets)
        })
    }

    companion object {
        const val ERROR_MESSAGE = "Sentimos muito, ocorreu um erro"
    }
}