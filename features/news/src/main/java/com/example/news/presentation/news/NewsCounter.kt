package com.example.news.presentation.news

import kotlinx.coroutines.flow.MutableStateFlow

object NewsCounterNews {
    private val unreadCount = MutableStateFlow(0)
    var readedNews = mutableListOf<com.example.data.model.listModel.Data>()

    fun onNewsRead() {
        val count = unreadCount.value
        if (count != null) {
            if (count > 0) {
                unreadCount.value = count - 1
            }
        }
    }

    fun onFilterChanged(count: Int) {
        unreadCount.value = count
    }

    fun getUnreadCountInt() = unreadCount.value
    fun getUnreadCountFlow() = unreadCount
}
