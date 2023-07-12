package com.example.data.localDataSource.repository

import android.app.Application
import com.example.data.localDataSource.room.NewsDataBase
import com.example.data.model.listModel.Data

class LocalRepositoryImpl(context: Application) {
    private val dao = NewsDataBase.getInstance(context).getDao()

    suspend fun getAllNews(): List<Data> {
        return dao.getAllNews()
    }

    suspend fun insertNews(list: List<Data>) {
        dao.insertNews(list)
    }
}
