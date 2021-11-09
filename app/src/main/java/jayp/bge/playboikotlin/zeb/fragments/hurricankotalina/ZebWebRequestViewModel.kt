package jayp.bge.playboikotlin.zeb.fragments.hurricankotalina

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ZebWebRequestViewModel : ViewModel() {

    private var viewState: ZebWebRequestViewState by Delegates.observable(ZebWebRequestViewState()) { _: KProperty<*>, _: ZebWebRequestViewState, viewState: ZebWebRequestViewState ->
        _viewState.postValue(viewState)
    }
    private val _viewState = MutableLiveData<ZebWebRequestViewState>()
    val viewStateLiveData: LiveData<ZebWebRequestViewState> = _viewState
    private var webService: ZebWebService? = null

    fun webRequestAddFortune(demand: String, id: Int, message: String, you_will_live_to: Int) {
        viewState = viewState.copy(webResponse = ZebLoading)
        viewModelScope.launch(Dispatchers.IO) {
            viewState = try {
                val data = webService?.addFortune(demand, id, message, you_will_live_to)
                viewState.copy(webResponse = ZebSuccess(data = data))
            } catch (e: Exception) {
                viewState.copy(webResponse = ZebFailure(e.message ?: e.toString()))
            }

        }
    }

    init {
        webService = Retrofit.Builder()
            .baseUrl("http://192.168.69.205:5000")
            .addConverterFactory(MoshiConverterFactory.create()) // what converts JSON to data class
            .build()
            .create(ZebWebService::class.java)
    }

}

data class ZebWebRequestViewState(
    val webResponse: ZebWebResponse = ZebSuccess()
)

sealed class ZebWebResponse

data class ZebSuccess(
    val data: List<EightBallJson>? = null
) : ZebWebResponse()

data class ZebFailure(
    val errorMessage: String
) : ZebWebResponse()

object ZebLoading : ZebWebResponse()