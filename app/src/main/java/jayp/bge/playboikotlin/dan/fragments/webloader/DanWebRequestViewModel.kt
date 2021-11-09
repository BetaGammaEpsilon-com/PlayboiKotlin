package jayp.bge.playboikotlin.dan.fragments.webloader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception
import kotlin.properties.Delegates.observable
import kotlin.reflect.KProperty

const val BASE_URL = "https://jsonplaceholder.typicode.com"
class DanWebRequestViewModel : ViewModel() {

    private var viewState: DanWebRequestViewState by observable(DanWebRequestViewState()){ _: KProperty<*>, _: DanWebRequestViewState, viewState: DanWebRequestViewState ->
        _viewState.postValue(viewState)
    }
    private val _viewState = MutableLiveData<DanWebRequestViewState>()
    val viewStateLiveData: LiveData<DanWebRequestViewState> = _viewState
    private var webService: DanWebService? = null

    fun sendWebRequest(id: Int) {
        viewState = viewState.copy(webResponse = Loading)
        viewModelScope.launch(Dispatchers.IO) {
            viewState = try {
                val data = webService?.getUsers(id)
                viewState.copy(webResponse = Success(data = data))
            } catch (e: Exception){
                viewState.copy(webResponse = Failure(e.message ?: e.toString()))
            }

        }
    }

    init {
        // we create the web service when we create he viewmodel
        webService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()) // what converts JSON to data class
            .build()
            .create(DanWebService::class.java) // connects to our service interface
    }
}

data class DanWebRequestViewState(
    val webResponse: WebResponse = Success()
)

sealed class WebResponse

data class Success(
    val data: List<User>? = null
    ) : WebResponse()

data class Failure(
    val errorMessage: String
    ) : WebResponse()

object Loading : WebResponse()