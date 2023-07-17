package com.example.search.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SearchViewModel(
    val context: Application,
    val getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
) : AndroidViewModel(context) {

    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<DomainData>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler) {
            newsList.value = getNewsFromDataBaseUseCase.execute()
        }
    }
}
