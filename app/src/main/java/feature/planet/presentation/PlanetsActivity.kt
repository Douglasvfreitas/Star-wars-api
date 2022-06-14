package feature.planet.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.starwars.R
import feature.planet.domain.models.PlanetsPresentation
import feature.planet.domain.models.Planet
import feature.utils.ScreenState
import feature.planet.presentation.adapter.PlanetAdapter
import feature.Params
import feature.character.presentation.CharactersDetailsActivity
import kotlinx.android.synthetic.main.activity_films.*

class PlanetsActivity : AppCompatActivity() {
    private val viewModel by lazy { PlanetsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        handleStates()
        viewModel.retrievePresentation()
        listenButton()
    }

    private fun listenButton() {
        retryButton.setOnClickListener {
            viewModel.retrievePresentation()
        }
    }

    fun handleStates() {
        viewModel.getScreenStatePlanet().observe(this, Observer { screenState ->
            when (screenState) {
                is ScreenState.Error -> handleError()
                is ScreenState.Loading -> handleLoading(true)
                is ScreenState.Result -> handleResult(screenState.value)
            }
        })
    }

    private fun handleLoading(isLoading: Boolean) {
        loadingState.isVisible = isLoading
        filmsRv.isVisible = !isLoading
        retryButton.isVisible = false
    }

    private fun handleResult(presentation: PlanetsPresentation) {
        handleLoading(false)
        filmsRv.adapter = PlanetAdapter(
            planets = presentation.planets, navigateToPlanetDetails = { planet: Planet ->
                moveToPlanetDetails(planet)
            }
        )
    }

    private fun handleError() {
        Toast.makeText(this, ERROR_MENSSAGE, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
        handleLoading(false)
        retryButton.isVisible = true
    }

    private fun moveToPlanetDetails(planets: Planet) {
        startActivity(Intent(this, CharactersDetailsActivity::class.java).apply {
            putExtra(Params.PLANET_FILM, planets)
        })
    }

    companion object {
        const val ERROR_MENSSAGE = "Sentimos muito, ocorreu um erro"
    }
}