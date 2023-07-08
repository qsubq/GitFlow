package com.example.javacoretraining.data.remoteDataSource.repository

import com.example.javacoretraining.data.model.listModel.ListModel
import com.example.javacoretraining.data.remoteDataSource.Retrofit
import com.example.javacoretraining.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RemoteRepositoryImpl : RemoteRepository {
    private val api = Retrofit.api

    override suspend fun getList(): Flow<Response<ListModel>> {
        return flow {
            emit(api.getList())
        }
    }
}
