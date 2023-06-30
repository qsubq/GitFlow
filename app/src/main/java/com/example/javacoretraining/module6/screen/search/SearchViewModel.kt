package com.example.javacoretraining.module6.screen.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.javacoretraining.module6.screen.news.JsonConverterIntoArray
import com.example.javacoretraining.module6.screen.news.NewsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel(private val context: Application) : AndroidViewModel(context) {
    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<NewsItem>> = MutableLiveData()

    fun getItemsJson(): List<NewsItem> {
        val gson = Gson()
        val jsonFileString =
            JsonConverterIntoArray().getJsonFromAssets(context, "Events.json")
        val typeToken = object : TypeToken<List<NewsItem>>() {}.type
        return gson.fromJson(jsonFileString, typeToken)
    }
}
