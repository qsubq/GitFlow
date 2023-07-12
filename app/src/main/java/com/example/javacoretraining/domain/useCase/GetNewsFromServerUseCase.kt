package com.example.javacoretraining.domain.useCase

import com.example.javacoretraining.data.model.listModel.ListModel
import com.example.javacoretraining.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetNewsFromServerUseCase(private val remoteRepository: RemoteRepository) {
    suspend fun execute(): Flow<Response<ListModel>> {
        return remoteRepository.getList()
    }
}
