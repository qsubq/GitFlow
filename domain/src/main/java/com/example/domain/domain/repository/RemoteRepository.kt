package com.example.domain.domain.repository

import com.example.domain.domain.model.listModel.DomainListModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteRepository {
    fun getList(): Flow<Response<DomainListModel>>
}