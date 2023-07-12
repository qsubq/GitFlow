package com.example.javacoretraining.domain.useCase

import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.domain.repository.LocalRepository

class GetNewsFromDataBaseUseCase(private val localRepositoryImpl: LocalRepository) {
    suspend fun execute(): List<Data> {
        return localRepositoryImpl.getAllNews()
    }
}
