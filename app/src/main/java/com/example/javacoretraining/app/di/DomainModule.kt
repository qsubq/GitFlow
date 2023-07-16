package com.example.javacoretraining.app.di

import com.example.javacoretraining.domain.repository.LocalRepository
import com.example.javacoretraining.domain.repository.RemoteRepository
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.javacoretraining.domain.useCase.GetNewsFromServerUseCase
import com.example.javacoretraining.domain.useCase.InsertNewsIntoDataBaseUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetNewsFromDataBaseUseCase(localRepository: LocalRepository): GetNewsFromDataBaseUseCase {
        return GetNewsFromDataBaseUseCase(localRepository)
    }

    @Singleton
    @Provides
    fun provideGetNewsFromServerUseCase(remoteRepository: RemoteRepository): GetNewsFromServerUseCase {
        return GetNewsFromServerUseCase(remoteRepository)
    }

    @Singleton
    @Provides
    fun provideInsertNewsIntoDataBaseUseCase(localRepository: LocalRepository): InsertNewsIntoDataBaseUseCase {
        return InsertNewsIntoDataBaseUseCase(localRepository)
    }
}
