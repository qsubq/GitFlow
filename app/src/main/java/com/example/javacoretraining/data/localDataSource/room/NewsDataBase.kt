package com.example.javacoretraining.data.localDataSource.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.javacoretraining.data.localDataSource.dao.NewsDao
import com.example.javacoretraining.data.model.listModel.Data

@Database(version = 1, entities = [Data::class])
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getDao(): NewsDao

    companion object {
        var dataBase: NewsDataBase? = null
        fun getInstance(context: Application): NewsDataBase {
            return if (dataBase == null) {
                dataBase = Room.databaseBuilder(context, NewsDataBase::class.java, "db").build()
                dataBase as NewsDataBase
            } else {
                dataBase as NewsDataBase
            }
        }
    }
}
