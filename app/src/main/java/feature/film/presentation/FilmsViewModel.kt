package feature.film.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import feature.film.data.FilmsInfra
import feature.film.domain.models.Film
import feature.utils.ScreenState

internal class FilmsViewModel : ViewModel() {

    private val service = FilmsInfra()
    private val mutableFilmState = MutableLiveData<ScreenState<List<Film>>>()
    fun getScreenState() = mutableFilmState as LiveData<ScreenState<List<Film>>>

    suspend fun retrieveMovies() {
        // TODO Implement state machine here
        service.listMovies()
    }
}