package com.example.javacoretraining.module6.screen.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsListViewModel : ViewModel() {
    val filters = MutableLiveData<Set<Int>>()
}
