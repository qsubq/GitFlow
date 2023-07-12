package com.example.javacoretraining.app.presentation.screen.news

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.javacoretraining.domain.useCase.GetNewsFromServerUseCase
import com.example.javacoretraining.domain.useCase.InsertNewsIntoDataBaseUseCase

class NewsViewModelFactory(
    val context: Context,
    val getNewsFromServerUseCase: GetNewsFromServerUseCase,
    val getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
    val insertNewsIntoDataBaseUseCase: InsertNewsIntoDataBaseUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsListViewModel(
            context as Application,
            getNewsFromServerUseCase,
            getNewsFromDataBaseUseCase,
            insertNewsIntoDataBaseUseCase,
        ) as T
    }
}
