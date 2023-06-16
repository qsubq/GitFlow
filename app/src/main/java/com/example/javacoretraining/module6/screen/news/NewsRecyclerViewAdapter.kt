package com.example.javacoretraining.module6.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.NewsRecyclerViewItemLayoutBinding

class NewsRecyclerViewAdapter() :
    ListAdapter<NewsItem, NewsRecyclerViewAdapter.NewsViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem == newItem
    }

    class NewsViewHolder(val binding: NewsRecyclerViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsRecyclerViewItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        // Пришлось сделать статичный аватар, т.к json не может содержать id
        holder.binding.imgNews.setImageResource(R.drawable.avatar_1)

        holder.binding.tvDate?.text = currentList[position].date
        holder.binding.tvTitle?.text = currentList[position].title
        holder.binding.tvDesc?.text = currentList[position].description

        holder.binding.linearLayoutMain.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("ParcelableNews", currentList[position])
            holder.itemView.findNavController()
                .navigate(R.id.action_containerFragment_to_detailNewsFragment, bundle)
        }
    }
}
