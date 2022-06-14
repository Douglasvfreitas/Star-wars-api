package feature.planet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import feature.planet.data.PlanetInfra
import feature.planet.domain.models.PlanetsPresentation
import feature.utils.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class PlanetsViewModel: ViewModel() {
    private val service = PlanetInfra()
    private val disposer = CompositeDisposable()
    private val mutablePlanetStats = MutableLiveData<ScreenState<PlanetsPresentation>>()
    fun getScreenStatePlanet() = mutablePlanetStats as LiveData<ScreenState<PlanetsPresentation>>

    override fun onCleared() {
        super.onCleared()
        disposer.clear()
    }

    fun retrievePresentation(){
        mutablePlanetStats.value = ScreenState.Loading
        disposer += service.listPlanet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
              onNext = { planets ->
                  mutablePlanetStats.value = ScreenState.Result(planets)
              },
                onError ={ error ->
                    mutablePlanetStats.value = ScreenState.Error(error)
                }

            )


    }
}