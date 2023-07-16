package com.example.domain.domain.repository

import com.example.domain.domain.model.listModel.DomainData

interface LocalRepository {

    suspend fun getAllNews(): List<DomainData>
    suspend fun insertNews(list: List<DomainData>)
}
