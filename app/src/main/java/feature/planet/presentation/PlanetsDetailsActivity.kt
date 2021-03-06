package feature.planet.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import feature.Params

import com.squareup.picasso.Picasso
import feature.planet.data.models.PlanetDetailsResponse
import kotlinx.android.synthetic.main.activity_planet_details.*


class PlanetsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_details)

        setupView()
    }

    private fun setupView() {
        val planet = intent.extras?.getSerializable(Params.PLANET_FILM) as? PlanetDetailsResponse

        planet?.let {
            setImage(planet.url)
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