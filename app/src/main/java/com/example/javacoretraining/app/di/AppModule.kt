package com.example.javacoretraining.app.di

import android.content.Context
import com.example.javacoretraining.app.presentation.screen.news.NewsViewModelFactory
import com.example.javacoretraining.app.presentation.screen.search.SearchViewModelFactory
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.javacoretraining.domain.useCase.GetNewsFromServerUseCase
import com.example.javacoretraining.domain.useCase.InsertNewsIntoDataBaseUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideNewsViewModelFactory(
        context: Context,
        getNewsFromServerUseCase: GetNewsFromServerUseCase,
        getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
        insertNewsIntoDataBaseUseCase: InsertNewsIntoDataBaseUseCase,
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            context,
            getNewsFromServerUseCase,
            getNewsFromDataBaseUseCase,
            insertNewsIntoDataBaseUseCase,
        )
    }

    @Provides
    fun provideSearchViewModelFactory(
        context: Context,
        getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
    ): SearchViewModelFactory {
        return SearchViewModelFactory(context, getNewsFromDataBaseUseCase)
    }
}
