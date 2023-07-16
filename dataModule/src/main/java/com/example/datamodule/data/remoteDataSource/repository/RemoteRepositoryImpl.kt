package com.example.datamodule.data.remoteDataSource.repository

import com.example.domain.domain.model.listModel.DomainListModel
import com.example.datamodule.data.remoteDataSource.Retrofit
import com.example.domain.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class RemoteRepositoryImpl : RemoteRepository {
    private val api = Retrofit.api

    override fun getList(): Flow<Response<DomainListModel>> {
        return flow {
            emit(api.getList())
        }
    }
}
