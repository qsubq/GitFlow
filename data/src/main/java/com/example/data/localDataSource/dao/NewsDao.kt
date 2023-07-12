package com.example.data.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.listModel.Data

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Data::class)
    suspend fun insertNews(list: List<Data>)

    @Query("SELECT * FROM Data")
    suspend fun getAllNews(): List<Data>
}
