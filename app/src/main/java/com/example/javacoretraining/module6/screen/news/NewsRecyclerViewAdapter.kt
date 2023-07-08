package com.example.javacoretraining.module6.screen.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.javacoretraining.R
import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.databinding.NewsRecyclerViewItemLayoutBinding
import com.example.javacoretraining.module6.screen.container.NewsCounter

class NewsRecyclerViewAdapter() :
    ListAdapter<Data, NewsRecyclerViewAdapter.NewsViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
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
//        when (position) {
//            0 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_1)
//            1 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_2)
//            2 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_3)
//            3 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_1)
//            4 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_2)
//            5 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_2)
//            6 -> holder.binding.imgNews.setImageResource(R.drawable.avatar_3)
//        }

        Glide.with(holder.itemView.context)
            .load(currentList[position].avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.binding.imgNews)

        holder.binding.tvDate?.text = currentList[position].email
        holder.binding.tvTitle?.text = currentList[position].first_name
        holder.binding.tvDesc?.text = currentList[position].last_name

        holder.binding.linearLayoutMain.setOnClickListener {
            if (!NewsCounter.readedNews.contains(currentList[position])) {
                Log.i("Tag", "OnNewsRead")
                NewsCounter.onNewsRead()
                NewsCounter.readedNews.add(currentList[position])
            }
            val bundle = Bundle()
            bundle.putParcelable("ParcelableNews", currentList[position])
            holder.itemView.findNavController()
                .navigate(R.id.action_containerFragment_to_detailNewsFragment, bundle)
        }
    }
}
