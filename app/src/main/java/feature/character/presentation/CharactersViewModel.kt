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
    private val mutableCharacterState = MutableLiveData<ScreenState<CharactersPresentation>>()
    fun getScreenStateCharacter() = mutableCharacterState as LiveData<ScreenState<CharactersPresentation>>

    suspend fun retrievePresentation() {
        // TODO implement state machine here
        service.listCharacter()
    }
}