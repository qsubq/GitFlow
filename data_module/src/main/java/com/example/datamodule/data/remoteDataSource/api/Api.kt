package com.example.datamodule.data.remoteDataSource.api

import com.example.domain.domain.model.listModel.DomainListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {

    @GET("/api/users?page=2")
    @Headers("accept: Application/json")
    suspend fun getList(): Response<DomainListModel>
}
