package com.example.starwars.screens.film

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.models.Film
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.film_item.view.*

class FilmsAdapter(private val films: List<Film>, val navigateToDetails: (Film) -> Unit = {}) :
    RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return FilmsViewHolder(item)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int = films.size

    inner class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(film: Film) {
            itemView.titleTv.text = film.title
            Picasso
                .get()
                .load(film.url)
                .error(R.drawable.episode1)
                .into(itemView.filmIv)
            itemView.episodeTv.text = film.retrieveEpisode()
            itemView.setOnClickListener { navigateToDetails(film) }
        }
    }
}



