package net.asgeri.softretrofit

import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class TodoRepository @Inject constructor(val api: TodoService, val gson: Gson) {

    suspend fun getTodoApi(): Response<TodoResponse> {
        return api.getTodoApi()
    }

}