package com.example.domain.domain.useCase

import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.repository.LocalRepository

class GetNewsFromDataBaseUseCase(private val localRepositoryImpl: LocalRepository) {
    suspend fun execute(): List<DomainData> {
        return localRepositoryImpl.getAllNews()
    }
}
