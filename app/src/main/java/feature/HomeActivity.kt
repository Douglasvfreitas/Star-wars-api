package feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import feature.character.presentation.CharactersActivity
import feature.film.presentation.FilmsActivity
import feature.planet.presentation.PlanetsActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        filmsInformTv.setOnClickListener { moveToFilmsList() }
        characterInformTv.setOnClickListener { moveToCharacterList() }
        planetInformTv.setOnClickListener { moveToPlanetsList() }
    }

    private fun moveToFilmsList() {
        startActivity(Intent(this, FilmsActivity::class.java))
    }

    private fun moveToCharacterList() {
        startActivity(Intent(this, CharactersActivity::class.java))
    }

    private fun moveToPlanetsList() {
        startActivity(Intent(this, PlanetsActivity::class.java))
    }
}