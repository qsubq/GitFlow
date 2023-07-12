package com.example.filter.presentation.filter

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.data.localDataSource.repository.LocalRepositoryImpl
import com.example.data.model.listModel.Data
import com.example.data.remoteDataSource.repository.RemoteRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler

class FilterViewModel(context: Application) : AndroidViewModel(context) {
    private val remoteRepository = RemoteRepositoryImpl()
    private val localRepository = LocalRepositoryImpl(context)

    val filtersCategory = MutableLiveData<Set<Int>>()
    val newsList: MutableLiveData<List<Data>> = MutableLiveData()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Maksim", "Поймано исключение: ${throwable.message}")
    }
}
