//package com.example.javacoretraining.app.di
//
//import android.content.Context
//import com.example.javacoretraining.app.presentation.screen.news.NewsViewModelFactory
//import com.example.javacoretraining.app.presentation.screen.search.SearchViewModelFactory
//import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
//import com.example.domain.domain.useCase.GetNewsFromServerUseCase
//import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
//import dagger.Module
//import dagger.Provides
//
//@Module
//class AppModule {
//
//    @Provides
//    fun provideNewsViewModelFactory(
//        context: Context,
//        getNewsFromServerUseCase: com.example.domain.domain.useCase.GetNewsFromServerUseCase,
//        getNewsFromDataBaseUseCase: com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase,
//        insertNewsIntoDataBaseUseCase: com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase,
//    ): NewsViewModelFactory {
//        return NewsViewModelFactory(
//            context,
//            getNewsFromServerUseCase,
//            getNewsFromDataBaseUseCase,
//            insertNewsIntoDataBaseUseCase,
//        )
//    }
//
//    @Provides
//    fun provideSearchViewModelFactory(
//        context: Context,
//        getNewsFromDataBaseUseCase: com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase,
//    ): SearchViewModelFactory {
//        return SearchViewModelFactory(context, getNewsFromDataBaseUseCase)
//    }
//}
