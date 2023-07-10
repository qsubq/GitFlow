package com.example.javacoretraining.data.localDataSource.repository

import android.app.Application
import com.example.javacoretraining.data.localDataSource.room.NewsDataBase
import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.domain.repository.LocalRepository

class LocalRepositoryImpl(context: Application) : LocalRepository {
    private val dao = NewsDataBase.getInstance(context).getDao()

    override suspend fun getAllNews(): List<Data> {
        return dao.getAllNews()
    }

    override suspend fun insertNews(list: List<Data>) {
        dao.insertNews(list)
    }
}
