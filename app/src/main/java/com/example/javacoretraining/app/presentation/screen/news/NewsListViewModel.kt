package com.example.javacoretraining.app.presentation.screen.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.domain.domain.useCase.GetNewsFromServerUseCase
import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val context: Application,
    val getNewsFromServerUseCase: GetNewsFromServerUseCase,
    val getNewsFromDataBaseUseCase: GetNewsFromDataBaseUseCase,
    val insertNewsIntoDataBaseUseCase: InsertNewsIntoDataBaseUseCase,
) : AndroidViewModel(context) {

    val filtersCategory = MutableLiveData<Set<Int>>()
    val newsList: MutableLiveData<List<DomainData>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromServer() {
        viewModelScope.launch(handler) {
            getNewsFromServerUseCase.execute().collect {
                if (it.code() == 200) {
                    it.body()?.data?.let { it1 -> insertNewsIntoDataBaseUseCase.execute(it1) }
                    newsList.value = getNewsFromDataBaseUseCase.execute()
                } else {
                    Log.e("Maksim", "Код ответа не равен 200")
                    newsList.value = getNewsFromDataBaseUseCase.execute()
                }
            }
        }
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler) {
            newsList.value = getNewsFromDataBaseUseCase.execute()
        }
    }
}
