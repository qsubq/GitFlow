package com.example.javacoretraining.module6.screen.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.javacoretraining.databinding.NewsRecyclerViewItemLayoutBinding

class NewsRecyclerViewAdapter : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {
    class NewsViewHolder(val binding: NewsRecyclerViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfNewsItems: List<NewsItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsRecyclerViewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfNewsItems.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.imgNews.setImageResource(listOfNewsItems[position].imgId)
        holder.binding.tvDate?.text = listOfNewsItems[position].date
        holder.binding.tvTitle?.text = listOfNewsItems[position].title
        holder.binding.tvDesc?.text = listOfNewsItems[position].description
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupAdaptersList(list: List<NewsItem>) {
        listOfNewsItems = list
        notifyDataSetChanged()
    }
}
