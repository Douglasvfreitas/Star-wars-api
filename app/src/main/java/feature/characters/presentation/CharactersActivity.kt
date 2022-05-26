package feature.characters.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.starwars.R
import com.example.starwars.infra.models.character.CharactersPresentation
import com.example.starwars.models.Character
import com.example.starwars.models.ScreenState
import com.example.starwars.params.Params
import feature.characters.presentation.models.CharacterAdapter
import feature.characters.presentation.steps.CharactersDetailsActivity
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
        viewModel.getScreenStateCharacter().observe(this, { screenState ->
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
            characters = presentation.characters,
            navigateToDetailsCharacters = { character: Character ->
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