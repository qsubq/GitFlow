package com.example.javacoretraining.app.presentation.screen.container

import com.example.javacoretraining.data.model.listModel.Data
import kotlinx.coroutines.flow.MutableStateFlow

object NewsCounter {
    private val unreadCount = MutableStateFlow(0)
    var readedNews = mutableListOf<Data>()

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
