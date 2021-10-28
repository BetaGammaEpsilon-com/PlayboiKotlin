package jayp.bge.playboikotlin.zeb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ZebViewModel : ViewModel() {

    private var zebViewState: ZebViewState by
    Delegates.observable(ZebViewState()) { _: KProperty<*>, _: ZebViewState, viewState: ZebViewState ->
        _zebViewStateLiveData.postValue(viewState)
    }

    private val _zebViewStateLiveData: MutableLiveData<ZebViewState> =
        MutableLiveData(ZebViewState())
    val zebViewStateLiveData: LiveData<ZebViewState> = _zebViewStateLiveData

    fun onButtonPress() {
        zebViewState = zebViewState.copy(zebViewState.clicks + 1)
    }

    fun onLongButtonPress() {

        zebViewState = zebViewState.copy(his = zebViewState.his.plus("hi"))
        viewModelScope.launch(Dispatchers.IO) {
            zebAsynchronousFunction()
        }
    }

    suspend fun zebAsynchronousFunction() {

        zebViewState = zebViewState.copy(loading = true)

        delay(5000)
        zebViewState = zebViewState.copy(tester = "i tested that", loading = false)
    }
}