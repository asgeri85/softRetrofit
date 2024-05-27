package net.asgeri.softretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TodoRepository
) : ViewModel() {

    val todos = MutableLiveData<List<Todo>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun getData() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.getTodoApi()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        response.body()?.todos?.let {
                            if (it.isNotEmpty()) {
                                todos.value = it
                            } else {
                                error.value = "Todo Tapılmadı "
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    error.value = e.localizedMessage ?: ""
                }
            } finally {
                withContext(Dispatchers.Main) {
                    loading.value = false
                }
            }
        }
    }

}
