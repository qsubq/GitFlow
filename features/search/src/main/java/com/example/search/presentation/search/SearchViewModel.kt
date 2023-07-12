package com.example.search.presentation.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.localDataSource.repository.LocalRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val context: Application) : AndroidViewModel(context) {

    private val localRepositoryImpl = LocalRepositoryImpl(context)

    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<com.example.data.model.listModel.Data>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            newsList.value = localRepositoryImpl.getAllNews()
        }
    }
}
