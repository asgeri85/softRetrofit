package net.asgeri.softretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val api = RetrofitClient.api

    val todos = MutableLiveData<List<Todo>>()




    fun getData() {
        viewModelScope.launch {
            val request = api.getTodoApi()
            if (request.isSuccessful) {

                request.body()?.todos?.let {
                    todos.value = it
                }
            }
        }

    }
}
