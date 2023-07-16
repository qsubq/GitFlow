//package com.example.javacoretraining.app.di
//
//import com.example.domain.domain.repository.LocalRepository
//import com.example.domain.domain.repository.RemoteRepository
//import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
//import com.example.domain.domain.useCase.GetNewsFromServerUseCase
//import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DomainModule {
//
//    @Provides
//    fun provideGetNewsFromDataBaseUseCase(localRepository: com.example.domain.domain.repository.LocalRepository): com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase {
//        return com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase(localRepository)
//    }
//
//    @Provides
//    fun provideGetNewsFromServerUseCase(remoteRepository: com.example.domain.domain.repository.RemoteRepository): com.example.domain.domain.useCase.GetNewsFromServerUseCase {
//        return com.example.domain.domain.useCase.GetNewsFromServerUseCase(remoteRepository)
//    }
//
//    @Provides
//    fun provideInsertNewsIntoDataBaseUseCase(localRepository: com.example.domain.domain.repository.LocalRepository): com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase {
//        return com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase(localRepository)
//    }
//}
