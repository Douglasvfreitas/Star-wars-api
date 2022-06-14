package feature.character.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.starwars.R
import feature.character.domain.models.CharactersPresentation
import feature.character.domain.models.Character
import feature.utils.ScreenState
import feature.Params
import feature.character.presentation.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.activity_films.*

class CharactersActivity : AppCompatActivity() {
    private val viewModel by lazy { CharactersViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        handleScreenStates()
        viewModel.retrievePresentation()
        listenButton()
    }

    private fun listenButton() {
        retryButton.setOnClickListener {
            viewModel.retrievePresentation()
        }
    }

    private fun handleScreenStates() {
        viewModel.getScreenStateCharacter().observe(this, Observer { screenState ->
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

    private fun handleResult(presentation: CharactersPresentation) {
        handleLoading(false)
        filmsRv.adapter = CharacterAdapter(
            characters = presentation.characters, navigateToDetailsCharacters = { character: Character ->
                moveToCharacterDetails(character)
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

    private fun moveToCharacterDetails(character: Character) {
        startActivity(Intent(this, CharactersDetailsActivity::class.java).apply {
            putExtra(Params.CHARACTER_FILM, character)
        })
    }

    companion object {
        const val ERROR_MENSSAGE = "Sentimos muito, ocorreu um erro"
    }
}