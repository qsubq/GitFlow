package com.example.javacoretraining.app.di

import android.app.Application
import android.content.Context
import com.example.javacoretraining.data.localDataSource.repository.LocalRepositoryImpl
import com.example.javacoretraining.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.javacoretraining.domain.repository.LocalRepository
import com.example.javacoretraining.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideLocalRepository(context: Context): LocalRepository {
        return LocalRepositoryImpl(context as Application)
    }

    @Provides
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepositoryImpl()
    }
}
