package jayp.bge.playboikotlin.dan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DanMainViewModel : ViewModel() {

    private val _viewState: MutableLiveData<DanMainViewState> = MutableLiveData(DanMainViewState())
    val viewStateLiveData: LiveData<DanMainViewState> = _viewState

    fun onButtonPressed() {
        val currViewState: DanMainViewState = _viewState.value ?: DanMainViewState()
        _viewState.postValue(currViewState.copy(currViewState.buttonCount + 1))
    }

    fun onButtonHeld() {
        val currViewState: DanMainViewState = _viewState.value ?: DanMainViewState()
        _viewState.postValue(currViewState.copy(currViewState.buttonCount - 1))
    }
}