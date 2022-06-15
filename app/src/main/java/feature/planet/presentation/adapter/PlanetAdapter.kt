package feature.planet.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import feature.planet.domain.models.Planet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.planet_item.view.*

class PlanetAdapter(
    private val planets: List<Planet>,
    val navigateToPlanetDetails: (Planet) -> Unit = {}
) :
    RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val item =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.planet_item, parent, false)
        return PlanetViewHolder(item)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount(): Int = planets.size

    inner class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(planets: Planet) {
            itemView.planetNameTv.text = planets.name
            Picasso
                .get()
                .load(planets.urlImage)
                .error(R.drawable.planet_error_img)
                .into(itemView.planetIv)

            itemView.setOnClickListener {
                navigateToPlanetDetails(planets)
            }
        }
    }
}