package feature.characters.presentation.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.models.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterAdapter(
    private val characters: List<Character>,
    val navigateToDetailsCharacters: (Character) -> Unit = {}
) : RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>() {

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
        fun bind(character: Character) {
            itemView.characterNameTv.text = character.name
            Picasso
                .get()
                .load(character.urlImage)
                .error(R.drawable.luke_skywalker)
                .into(itemView.characterIv)

            itemView.setOnClickListener {
                navigateToDetailsCharacters(character)
            }
        }
    }
}