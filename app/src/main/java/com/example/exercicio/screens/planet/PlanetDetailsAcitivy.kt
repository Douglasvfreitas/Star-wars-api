package com.example.exercicio.screens.planet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio.R
import com.example.exercicio.models.Planet
import com.example.exercicio.params.Params
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.android.synthetic.main.activity_planet_details.*

class PlanetsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_details)

        setupView()
    }

    private fun setupView() {
        val planet = intent.extras?.getSerializable(Params.PLANET_FILM) as? Planet

        planet?.let {
            setImage(planet.urlImage)
            planetNameDetails.text = planet.name
            rotationDetails.text = planet.rotation
            orbitalPeriodDetails.text = planet.orbital
            diameterDetails.text = planet.diameter
            climateDetails.text = planet.climate
            gravityDetails.text = planet.gravity
            terrainDetails.text = planet.terrain
            surfaceWaterDetails.text = planet.water
            populationDetails.text = planet.population
        }
    }

    private fun setImage(urlImage: String) {
        Picasso
            .get()
            .load(urlImage)
            .error(R.drawable.planet_error_img)
            .into(planetImage)
    }
}