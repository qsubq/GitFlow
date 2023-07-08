package com.example.javacoretraining.data.remoteDataSource.repository

import com.example.javacoretraining.data.model.listModel.ListModel
import com.example.javacoretraining.data.remoteDataSource.Retrofit
import com.example.javacoretraining.domain.repository.RemoteRepository
import retrofit2.Response

class RemoteRepositoryImpl : RemoteRepository {
    private val api = Retrofit.api

    override suspend fun getList(): Response<ListModel> {
        return api.getList()
    }
}
