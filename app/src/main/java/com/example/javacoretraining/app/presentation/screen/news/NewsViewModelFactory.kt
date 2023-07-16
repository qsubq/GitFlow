package com.example.javacoretraining.app.presentation.screen.news

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.domain.domain.useCase.GetNewsFromServerUseCase
import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase

class NewsViewModelFactory(
    val context: Context,
    val getNewsFromServerUseCase: com.example.domain.domain.useCase.GetNewsFromServerUseCase,
    val getNewsFromDataBaseUseCase: com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase,
    val insertNewsIntoDataBaseUseCase: com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsListViewModel(
            context.applicationContext as Application,
            getNewsFromServerUseCase,
            getNewsFromDataBaseUseCase,
            insertNewsIntoDataBaseUseCase,
        ) as T
    }
}
