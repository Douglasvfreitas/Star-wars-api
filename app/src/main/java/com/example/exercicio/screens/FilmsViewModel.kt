package com.example.exercicio.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercicio.infra.RetrofitClient
import com.example.exercicio.infra.StarWarsGateway
import com.example.exercicio.models.Film
import com.example.exercicio.models.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class FilmsViewModel : ViewModel() {

    private val remote = RetrofitClient.createService(StarWarsGateway::class.java)
    private val disposer = CompositeDisposable()
    private val mutableFilmState = MutableLiveData<ScreenState<List<Film>>>()
    fun getScreenState() = mutableFilmState as LiveData<ScreenState<List<Film>>>

    override fun onCleared() {
        super.onCleared()
        disposer.clear()
    }

    fun retrieveMovies() {
        mutableFilmState.value = ScreenState.Loading
        disposer += remote.listMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { filmsResponse ->
                    filmsResponse.result.map { filmModel ->
                        Film(
                            title = filmModel.title,
                            episodeId = filmModel.episodeId,
                            openingCrawl = filmModel.openingCrawl,
                            director = filmModel.director,
                            producer = filmModel.producer,
                            releaseDate = filmModel.releaseDate,
                            characters = filmModel.characters.orEmpty(),
                            url = filmModel.url
                        )
                    }
                        .let { films ->
                            mutableFilmState.value = ScreenState.Result(films)
                        }
                },
                onError = { error ->
                    mutableFilmState.value = ScreenState.Error(error)
                }
            )
    }

}