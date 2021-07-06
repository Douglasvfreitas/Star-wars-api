package com.example.exercicio.screens.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercicio.R
import com.example.exercicio.models.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterAdapter(
    private val characters: List<Person>,
    val navigateToDetailsCharacters: (Person) -> Unit = {}
) :
    RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val item =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.character_item, parent, false)
        return CharactersViewHolder(item)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(person: Person) {
            itemView.characterNameTv.text = person.name
            Picasso
                .get()
                .load(person.urlImage)
                .error(R.drawable.luke_skywalker)
                .into(itemView.characterIv)

            itemView.setOnClickListener {
                navigateToDetailsCharacters(person)
            }
        }
    }
}