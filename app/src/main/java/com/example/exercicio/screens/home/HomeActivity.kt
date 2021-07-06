package com.example.exercicio.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio.R
import com.example.exercicio.screens.character.CharactersActivity
import com.example.exercicio.screens.film.FilmsActivity
import com.example.exercicio.screens.planet.PlanetsActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        filmsInformTv.setOnClickListener { moveToFilmsList() }
        characterInformTv.setOnClickListener { moveToCharacterList() }
        planetInformTv.setOnClickListener{ moveToPlanetsList() }
    }

    private fun moveToFilmsList() {
        startActivity(Intent(this, FilmsActivity::class.java))
    }

    private fun moveToCharacterList() {
        startActivity(Intent(this, CharactersActivity::class.java))
    }

    private fun moveToPlanetsList(){
        startActivity(Intent(this,PlanetsActivity::class.java))
    }
}