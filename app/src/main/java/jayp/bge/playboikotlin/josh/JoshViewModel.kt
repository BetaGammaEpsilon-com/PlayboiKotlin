package jayp.bge.playboikotlin.josh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JoshViewModel : ViewModel() {

    private val _joshLiveData: MutableLiveData<JoshViewState> = MutableLiveData(JoshViewState())

    private var i = 0

    val joshLiveData: LiveData<JoshViewState> = _joshLiveData

    fun onJButtonPressed() {
        _joshLiveData.postValue(_joshLiveData.value!!.copy(_joshLiveData.value!!.clicks + 1))
        _joshLiveData.postValue(_joshLiveData.value!!.copy(name = "loading"))
        viewModelScope.launch(Dispatchers.IO) {
            suspendJosh()
        }
    }

    fun onNButtonPressed() {
        val names: List<String> = listOf("jayp", "zeb", "robbie", "josh")
        i++

        _joshLiveData.postValue(_joshLiveData.value!!.copy(name = names[i % names.size]))
    }

    suspend fun suspendJosh() {
        _joshLiveData.postValue(_joshLiveData.value!!.copy(isLoading = true))
        delay(5000)
        _joshLiveData.postValue(_joshLiveData.value!!.copy(isLoading = false))
    }


}