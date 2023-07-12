package com.example.news.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.localDataSource.repository.LocalRepositoryImpl
import com.example.data.remoteDataSource.repository.RemoteRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListViewModel(context: Application) : AndroidViewModel(context) {
    private val remoteRepository = RemoteRepositoryImpl()
    private val localRepository = LocalRepositoryImpl(context)

    val filtersCategory = MutableLiveData<Set<Int>>()
    val newsList: MutableLiveData<List<com.example.data.model.listModel.Data>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }

    fun getListFromServer() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            remoteRepository.getList().collect {
                if (it.code() == 200) {
                    it.body()?.data?.let { it1 -> localRepository.insertNews(it1) }
                    newsList.value = localRepository.getAllNews()
                } else {
                    Log.e("Maksim", "Код ответа не равен 200")
                    newsList.value = localRepository.getAllNews()
                }
            }
        }
    }

    fun getListFromDataBase() {
        viewModelScope.launch(handler) {
            newsList.value = localRepository.getAllNews()
        }
    }
}
