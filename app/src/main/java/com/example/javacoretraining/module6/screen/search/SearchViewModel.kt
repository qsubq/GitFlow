package com.example.javacoretraining.module6.screen.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.javacoretraining.data.model.NewsItem
import com.example.javacoretraining.data.model.listModel.ListModel
import com.example.javacoretraining.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.javacoretraining.module6.screen.news.JsonConverterIntoArray
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(private val context: Application) : AndroidViewModel(context) {
    private val repository = RemoteRepositoryImpl()

    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val newsListFromServer: MutableLiveData<Response<ListModel>> = MutableLiveData()

    fun getItemsJson(): List<NewsItem> {
        val gson = Gson()
        val jsonFileString =
            JsonConverterIntoArray().getJsonFromAssets(context, "Events.json")
        val typeToken = object : TypeToken<List<NewsItem>>() {}.type
        return gson.fromJson(jsonFileString, typeToken)
    }

    fun getList() {
        viewModelScope.launch {
            newsListFromServer.value = repository.getList()
        }
    }
}
