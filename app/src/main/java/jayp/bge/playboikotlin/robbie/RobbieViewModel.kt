package jayp.bge.playboikotlin.robbie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class RobbieViewModel : ViewModel() {

    // update the public viewStateLiveData
    private var viewState: RobbieViewState by Delegates.observable(RobbieViewState()) { _: KProperty<*>, _: RobbieViewState, viewState: RobbieViewState ->
        _viewState.postValue(viewState)
    }

    private val _viewState: MutableLiveData<RobbieViewState> = MutableLiveData(RobbieViewState())
    val robbieViewStateLiveData: LiveData<RobbieViewState> = _viewState

    // if i KNOW it has a value, use _robbieViewStateLiveData.value!!.copy ...... _robbieViewStateLiveData.value!!.copy .....
    // sort of bad practice maybe ?
    // old
    //    _robbieViewStateLiveData.value?.copy(
    //        clicks = _robbieViewStateLiveData.value?.clicks ?: 0 + 1
    //      )
    //   )
    fun onButtonClick() {
        viewState = viewState.copy(clicks = viewState.clicks + 1, robbieString = null)
    }

    fun onLongButtonClick() {
        viewState = viewState.copy(
            clicks = viewState.clicks - 1,
            robbieString = "you subtracted! congrats!"
        )
        // call robbieAsyncFun on background thread
        viewModelScope.launch(Dispatchers.IO) {
            robbieAsyncFun()
        }
    }

    private suspend fun robbieAsyncFun() {
        for (i in 5 downTo 1) {
            viewState = viewState.copy(robbieString = "robbie async loading... $i", loading = true)
            delay(1000)
        }
        viewState = viewState.copy(robbieString = "robbie async function time!", loading = false)
    }
}