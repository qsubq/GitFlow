package com.example.javacoretraining.domain.useCase

import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.domain.repository.LocalRepository

class InsertNewsIntoDataBaseUseCase(private val localRepositoryImpl: LocalRepository) {
    suspend fun execute(list: List<Data>) {
        return localRepositoryImpl.insertNews(list)
    }
}
