package com.example.datamodule.data.localDataSource.repository

import android.app.Application
import com.example.datamodule.data.localDataSource.room.NewsDataBase
import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.repository.LocalRepository

class LocalRepositoryImpl(context: Application) :
    LocalRepository {
    private val dao = NewsDataBase.getInstance(context).getDao()

    override suspend fun getAllNews(): List<DomainData> {
        return dao.getAllNews() as List<DomainData>
    }

    override suspend fun insertNews(list: List<DomainData>) {
        dao.insertNews(list as List<DomainData>)
    }
}
