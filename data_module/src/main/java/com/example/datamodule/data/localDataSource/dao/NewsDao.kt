package com.example.datamodule.data.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.domain.model.listModel.DomainData

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DomainData::class)
    suspend fun insertNews(list: List<DomainData>)

    @Query("SELECT * FROM DomainData")
    suspend fun getAllNews(): List<DomainData>
}
