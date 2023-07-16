package com.example.domain.domain.useCase

import com.example.domain.domain.model.listModel.DomainListModel
import com.example.domain.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetNewsFromServerUseCase(private val remoteRepository: RemoteRepository) {
    suspend fun execute(): Flow<Response<DomainListModel>> {
        return remoteRepository.getList()
    }
}
