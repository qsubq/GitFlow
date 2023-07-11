package com.example.javacoretraining.app.presentation.screen.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.javacoretraining.data.localDataSource.repository.LocalRepositoryImpl
import com.example.javacoretraining.data.model.listModel.Data
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(context: Application) : AndroidViewModel(context) {
    private val localRepository = LocalRepositoryImpl(context)

    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<Data>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            newsList.value = localRepository.getAllNews()
        }
    }
}
