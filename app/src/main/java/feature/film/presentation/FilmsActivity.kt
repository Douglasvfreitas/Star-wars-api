package feature.film.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.starwars.R
import feature.Params
import feature.film.domain.models.Film
import feature.film.domain.models.FilmsPresentation
import feature.film.presentation.adapter.FilmsAdapter
import feature.utils.StateMachine
import feature.utils.collectIn
import kotlinx.android.synthetic.main.activity_films.*

class FilmsActivity : AppCompatActivity() {

    private val viewModel by lazy { FilmsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        handleScreenStates()
        listenButton()
    }

    private fun handleScreenStates() {
        viewModel.retrieveMovies().collectIn(lifecycleScope) { event ->
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

    private fun listenButton() {
        retryButton.setOnClickListener {
            viewModel.retrieveMovies()
        }
    }

    private fun handleResult(presentation: FilmsPresentation) {
        handleLoading(false)
        filmsRv.adapter = FilmsAdapter(
            films = presentation.films, navigateToDetails = { film ->
                moveToFilmDetails(film)
            })
    }

    private fun handleError() {
        Toast.makeText(this, ERROR_MENSSAGE, Toast.LENGTH_LONG).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
        handleLoading(false)
        retryButton.isVisible = true
    }

    private fun moveToFilmDetails(film: Film) {
        startActivity(Intent(this, FilmDetailsActivity::class.java).apply {
            putExtra(Params.FILM_FILM, film)
        })
    }

    companion object {
        const val ERROR_MENSSAGE = "Sentimos muito, ocorreu um erro"
    }
}