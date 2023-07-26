//package com.example.datamodule.data.di
//
//import android.app.Application
//import android.content.Context
//import com.example.datamodule.data.localDataSource.repository.LocalRepositoryImpl
//import com.example.datamodule.data.remoteDataSource.repository.RemoteRepositoryImpl
//import com.example.domain.domain.repository.LocalRepository
//import com.example.domain.domain.repository.RemoteRepository
//import dagger.Module
//import dagger.Provides
//
//@Module
//class DataModule(val context: Context) {
//
//    @Provides
//    fun provideContext(): Context {
//        return context
//    }
//
//    @Provides
//    fun provideLocalRepository(context: Context): com.example.domain.domain.repository.LocalRepository {
//        return LocalRepositoryImpl(context as Application)
//    }
//
//    @Provides
//    fun provideRemoteRepository(): com.example.domain.domain.repository.RemoteRepository {
//        return RemoteRepositoryImpl()
//    }
//}
