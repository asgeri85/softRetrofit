package net.asgeri.softretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {

    @GET("todos")
    suspend fun getTodoApi(): Response<TodoResponse>

}