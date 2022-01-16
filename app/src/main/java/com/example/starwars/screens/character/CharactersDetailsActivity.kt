package com.example.starwars.screens.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars.R
import com.example.starwars.models.Character
import com.example.starwars.params.Params
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_details.*

class CharactersDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        setupView()
    }

    private fun setupView() {
        val characters = intent.extras?.getSerializable(Params.CHARACTER_FILM) as? Character

        characters?.let {
            setImage(characters.urlImage)
            characterNameDetails.text = characters.name
            heightDetails.text = characters.height
            massDetails.text = characters.mass
            hairColorDetails.text = characters.hairColor
            skinColorDetails.text = characters.skinColor
            eyeColorDetails.text = characters.eyeColor
            birthYearDetails.text = characters.birthYear
            genderDetails.text = characters.gender
        }
    }

    private fun setImage(urlImage: String) {
        Picasso
            .get()
            .load(urlImage)
            .error(R.drawable.luke_skywalker)
            .into(characterImage)
    }
}