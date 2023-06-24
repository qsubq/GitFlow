package com.example.javacoretraining.module6.screen.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.javacoretraining.module6.screen.news.JsonConverterIntoArray
import com.example.javacoretraining.module6.screen.news.NewsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable

class SearchViewModel(private val context: Application) : AndroidViewModel(context) {
    val searchString = MutableLiveData<String>()
    val newsList: MutableLiveData<List<NewsItem>> = MutableLiveData()

    fun getItemsFromJson(): Observable<List<NewsItem>> {
        return Observable.unsafeCreate { subscriber ->
            val gson = Gson()
            val jsonFileString =
                JsonConverterIntoArray().getJsonFromAssets(context, "Events.json")
            val typeToken = object : TypeToken<List<NewsItem>>() {}.type
            val result: List<NewsItem> =
                gson.fromJson(jsonFileString, typeToken)
            subscriber.onNext(result)
            subscriber.onComplete()
        }
    }
}
