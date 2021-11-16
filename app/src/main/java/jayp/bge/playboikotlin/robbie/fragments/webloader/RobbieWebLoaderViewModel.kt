package jayp.bge.playboikotlin.robbie.fragments.webloader

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

class RobbieWebLoaderViewModel : ViewModel() {

    private var viewState: RobbieWebLoaderViewState by observable(RobbieWebLoaderViewState()) { _: KProperty<*>, _: RobbieWebLoaderViewState, viewState: RobbieWebLoaderViewState ->
        _viewState.postValue(viewState)
    }
    private val _viewState = MutableLiveData<RobbieWebLoaderViewState>()
    val viewStateLiveData: LiveData<RobbieWebLoaderViewState> = _viewState
    private var webService: RobbieWebService? = null

    fun sendWebRequest(id: Int) {
        viewState = viewState.copy(webResponse = Loading)
        viewModelScope.launch(Dispatchers.IO) {
            viewState = try {
                val data = webService?.robbieInsert8Ball("rippin kotlin as per usual",
                    11820221,
                    21,
                    "look at me")
                viewState.copy(webResponse = Success(data = data))
            } catch (e: Exception) {
                viewState.copy(webResponse = Failure(e.message ?: e.toString()))
            }
        }
    }

    init {
        webService = Retrofit.Builder()
            .baseUrl("http://192.168.69.205:5000/api/v2/8ball/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RobbieWebService::class.java)
    }
}

data class RobbieWebLoaderViewState(
    val webResponse: RobResponse = Success(),
)

sealed class RobResponse

data class Success(
    val data: List<EightBall>? = null,
) : RobResponse()

data class Failure(
    val errorMessage: String,
) : RobResponse()

object Loading : RobResponse()
