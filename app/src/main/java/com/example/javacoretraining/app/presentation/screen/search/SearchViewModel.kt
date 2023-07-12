package com.example.javacoretraining.app.presentation.screen.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.domain.useCase.GetNewsFromDataBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel(
    val context: Application,
    val getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
) : AndroidViewModel(context) {

    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<Data>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler) {
            newsList.value = getNewsFromDataBaseUseCase.execute()
        }
    }
}
