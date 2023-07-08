package com.example.javacoretraining.domain.repository

import com.example.javacoretraining.data.model.listModel.ListModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteRepository {
    suspend fun getList(): Flow<Response<ListModel>>
}