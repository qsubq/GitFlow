package com.example.javacoretraining.app.presentation.screen.search

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase

class SearchViewModelFactory(
    val context: Context,
    val getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(
            context as Application,
            getNewsFromDataBaseUseCase,
        ) as T
    }
}
