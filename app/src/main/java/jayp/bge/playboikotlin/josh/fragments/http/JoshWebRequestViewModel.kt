package jayp.bge.playboikotlin.josh.fragments.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

const val DANPOINT_URL = "http://192.168.69.205:5000"

class JoshWebRequestViewModel : ViewModel() {

    private var viewState: JoshWebRequestViewState by observable(JoshWebRequestViewState()) { _: KProperty<*>, _: JoshWebRequestViewState, viewState: JoshWebRequestViewState ->
        _viewState.postValue(viewState)
    }
    private val _viewState = MutableLiveData<JoshWebRequestViewState>()
    val viewStateliveData: LiveData<JoshWebRequestViewState> = _viewState
    private var webService: JoshWebService? = null

    fun sendShakeRequest() {
        viewState = viewState.copy(webResponse = Loading)
        viewModelScope.launch(Dispatchers.IO) {
            viewState = try {
                val data = webService?.queryAteBall()
                viewState.copy(webResponse = JoshSuccess(data = data))
            } catch (e: Exception) {
                viewState.copy(webResponse = JoshFailure(e.message ?: e.toString()))
            }
        }
    }

    fun sendAddRequest(demand: String, id: Int, message: String, you_will_live_to: Int) {
        viewState = viewState.copy(webResponse = Loading)
        viewModelScope.launch(Dispatchers.IO) {
            viewState = try {
                val listData = webService?.addAteBall(demand, id, message, you_will_live_to)
                viewState.copy(webResponse = JoshSuccess(listData = listData))
            } catch (e: Exception) {
                viewState.copy(webResponse = JoshFailure(e.message ?: e.toString()))
            }
        }
    }

    init {
        webService = Retrofit.Builder()
            .baseUrl(DANPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(JoshWebService::class.java)
    }
}

data class JoshWebRequestViewState(
    val webResponse: JoshWebResponse = JoshSuccess()
)

sealed class JoshWebResponse

data class JoshSuccess(
    val data: AteBall? = null,
    val listData: List<AteBall>? = null
) : JoshWebResponse()

data class JoshFailure(
    val errorMessage: String
) : JoshWebResponse()

object Loading : JoshWebResponse()
