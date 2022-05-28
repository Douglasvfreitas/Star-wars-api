package feature.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import feature.character.data.CharacterInfra
import feature.character.domain.models.CharactersPresentation
import com.example.starwars.models.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class CharactersViewModel : ViewModel() {

    private val service = CharacterInfra()
    private val disposer = CompositeDisposable()
    private val mutableCharacterState = MutableLiveData<ScreenState<CharactersPresentation>>()
    fun getScreenStateCharacter() = mutableCharacterState as LiveData<ScreenState<CharactersPresentation>>

    override fun onCleared() {
        super.onCleared()
        disposer.clear()
    }

    fun retrievePresentation() {
        mutableCharacterState.value = ScreenState.Loading
        disposer += service.listCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { character ->
                    mutableCharacterState.value = ScreenState.Result(character)
                },
                onError = { error ->
                    mutableCharacterState.value = ScreenState.Error(error)
                }
            )
    }
}