package jayp.bge.playboikotlin.dan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

class DanMainViewModel : ViewModel() {

    private var viewState: DanMainViewState by observable(DanMainViewState()) { _: KProperty<*>, _: DanMainViewState, viewState: DanMainViewState ->
        _viewState.postValue(viewState)
    }
    private val _viewState: MutableLiveData<DanMainViewState> = MutableLiveData()
    val viewStateLiveData: LiveData<DanMainViewState> = _viewState

    fun onButtonPressed() {
        viewState = viewState.copy(buttonCount = viewState.buttonCount + 1)
    }

    fun onButtonHeld() {
        viewState = viewState.copy(buttonCount = viewState.buttonCount - 1)
        viewModelScope.launch(Dispatchers.IO) {
            delayedFunction()
        }
    }

    private suspend fun delayedFunction() {
        delay(10000)
        viewState = viewState.copy(delayedMessage = "Ayy you kept your threads safe!")
    }
}