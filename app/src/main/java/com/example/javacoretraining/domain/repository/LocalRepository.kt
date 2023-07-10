package com.example.javacoretraining.domain.repository

import com.example.javacoretraining.data.model.listModel.Data

interface LocalRepository {

    suspend fun getAllNews(): List<Data>
    suspend fun insertNews(list: List<Data>)
}
