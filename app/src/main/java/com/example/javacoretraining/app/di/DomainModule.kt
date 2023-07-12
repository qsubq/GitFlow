package com.example.javacoretraining.app.di

import com.example.javacoretraining.domain.repository.LocalRepository
import com.example.javacoretraining.domain.repository.RemoteRepository
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.javacoretraining.domain.useCase.GetNewsFromServerUseCase
import com.example.javacoretraining.domain.useCase.InsertNewsIntoDataBaseUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetNewsFromDataBaseUseCase(localRepository: LocalRepository): GetNewsFromDataBaseUseCase {
        return GetNewsFromDataBaseUseCase(localRepository)
    }

    @Provides
    fun provideGetNewsFromServerUseCase(remoteRepository: RemoteRepository): GetNewsFromServerUseCase {
        return GetNewsFromServerUseCase(remoteRepository)
    }

    @Provides
    fun provideInsertNewsIntoDataBaseUseCase(localRepository: LocalRepository): InsertNewsIntoDataBaseUseCase {
        return InsertNewsIntoDataBaseUseCase(localRepository)
    }
}
