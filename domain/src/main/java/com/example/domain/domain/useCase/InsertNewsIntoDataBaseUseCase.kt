package com.example.domain.domain.useCase

import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.repository.LocalRepository

class InsertNewsIntoDataBaseUseCase(private val localRepositoryImpl: LocalRepository) {
    suspend fun execute(list: List<DomainData>) {
        return localRepositoryImpl.insertNews(list)
    }
}
