package feature.film.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import feature.film.data.FilmsInfra
import feature.film.domain.models.Film
import com.example.starwars.models.ScreenState
import com.example.starwars.infra.FilmsInfra
import com.example.starwars.models.Film
import feature.utils.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class FilmsViewModel : ViewModel() {

    private val service = FilmsInfra()
    private val disposer = CompositeDisposable()
    private val mutableFilmState = MutableLiveData<ScreenState<List<Film>>>()
    fun getScreenState() = mutableFilmState as LiveData<ScreenState<List<Film>>>

    override fun onCleared() {
        super.onCleared()
        disposer.clear()
    }

    fun retrieveMovies() {
        mutableFilmState.value = ScreenState.Loading
        disposer += service.listMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { films ->
                    mutableFilmState.value = ScreenState.Result(films)
                },
                onError = { error ->
                    mutableFilmState.value = ScreenState.Error(error)
                }
            )
    }
}