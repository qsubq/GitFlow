package com.example.javacoretraining.module6.screen.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors

class NewsListViewModel(private val context: Application) : AndroidViewModel(context) {
    val filters = MutableLiveData<Set<Int>>()
    val newsList: MutableLiveData<List<NewsItem>> = MutableLiveData()

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
            newsList.postValue(newsItemFormJson)
        }
        executor.execute(worker)
        executor.shutdown()
    }
}
