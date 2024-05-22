package net.asgeri.softretrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    val test:String = "dsdfsfs"

    companion object {

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(TodoService::class.java)

    }

}