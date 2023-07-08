package com.example.javacoretraining.module6.screen.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.javacoretraining.data.model.NewsItem
import com.example.javacoretraining.data.model.listModel.ListModel
import com.example.javacoretraining.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.concurrent.Executors

class NewsListViewModel(private val context: Application) : AndroidViewModel(context) {
    private val remoteRepository = RemoteRepositoryImpl()

    val filtersCategory = MutableLiveData<Set<Int>>()
    val newsListJson: MutableLiveData<List<NewsItem>> = MutableLiveData()
    val newsListFromServer: MutableLiveData<Response<ListModel>> = MutableLiveData()

    fun getListFromJson() {
        val executor = Executors.newSingleThreadExecutor()
        val worker = Runnable {
            Thread.sleep(5000)
            val gson = Gson()
            val jsonFileString =
                JsonConverterIntoArray().getJsonFromAssets(context, "Events.json")
            val typeToken = object : TypeToken<List<NewsItem>>() {}.type
            val newsItemFormJson: List<NewsItem> =
                gson.fromJson(jsonFileString, typeToken)
            newsListJson.postValue(newsItemFormJson)
        }
        executor.execute(worker)
        executor.shutdown()
    }

    fun getListFromServer() {
        viewModelScope.launch {
            newsListFromServer.value = remoteRepository.getList()
        }
    }
}
