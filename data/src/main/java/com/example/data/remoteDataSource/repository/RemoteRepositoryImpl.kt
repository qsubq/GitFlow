package com.example.data.remoteDataSource.repository

import com.example.data.model.listModel.ListModel
import com.example.data.remoteDataSource.Retrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RemoteRepositoryImpl {
    private val api = Retrofit.api

    fun getList(): Flow<Response<ListModel>> {
        return flow {
            emit(api.getList())
        }
    }
}
