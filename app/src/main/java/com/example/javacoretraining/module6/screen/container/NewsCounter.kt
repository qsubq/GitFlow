package com.example.javacoretraining.module6.screen.container

import com.example.javacoretraining.module6.screen.news.NewsItem
import io.reactivex.rxjava3.subjects.BehaviorSubject

object NewsCounter {
    private val unreadCount = BehaviorSubject.createDefault(0)
    val readedNews = mutableListOf<NewsItem>()

    fun onNewsRead() {
        val count = unreadCount.value
        if (count != null) {
            if (count > 0) {
                unreadCount.onNext(count - 1)
            }
        }
    }

    fun onFilterChanged(count: Int) {
        unreadCount.onNext(count)
    }
    fun getUnreadCountInt() = unreadCount.value
    fun getUnreadCount() = unreadCount.hide()
}
