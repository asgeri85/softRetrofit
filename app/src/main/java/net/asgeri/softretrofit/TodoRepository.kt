package net.asgeri.softretrofit

import retrofit2.Response

class TodoRepository {

    private val api = RetrofitClient.api

    suspend fun getTodoApi(): Response<TodoResponse> {
        return api.getTodoApi()
    }

}