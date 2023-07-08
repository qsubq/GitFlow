package com.example.javacoretraining.data.remoteDataSource.api

import com.example.javacoretraining.data.model.listModel.ListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @GET("/api/users?page=2")
    @Headers("accept: Application/json")
    suspend fun getList(): Response<ListModel>
}
