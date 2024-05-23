package net.asgeri.softretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    val repo = TodoRepository()

    val todos = MutableLiveData<List<Todo>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

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
