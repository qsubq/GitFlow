package com.example.javacoretraining.app.di

import android.app.Application
import android.content.Context
import com.example.javacoretraining.data.localDataSource.repository.LocalRepositoryImpl
import com.example.javacoretraining.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.javacoretraining.domain.repository.LocalRepository
import com.example.javacoretraining.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideLocalRepository(context: Context): LocalRepository {
        return LocalRepositoryImpl(context as Application)
    }

    @Singleton
    @Provides
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepositoryImpl()
    }
}
