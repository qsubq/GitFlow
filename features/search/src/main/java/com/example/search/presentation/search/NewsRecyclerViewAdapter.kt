package com.example.search.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.search.databinding.NewsRecyclerViewItemLayoutBinding

class NewsRecyclerViewAdapter :
    ListAdapter<com.example.data.model.listModel.Data, NewsRecyclerViewAdapter.NewsViewHolder>(
        DiffCallback(),
    ) {

    private class DiffCallback : DiffUtil.ItemCallback<com.example.data.model.listModel.Data>() {
        override fun areItemsTheSame(
            oldItem: com.example.data.model.listModel.Data,
            newItem: com.example.data.model.listModel.Data,
        ) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: com.example.data.model.listModel.Data,
            newItem: com.example.data.model.listModel.Data,
        ) =
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
        Glide.with(holder.itemView.context)
            .load(currentList[position].avatar)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.binding.imgNews)

        holder.binding.tvDate.text = currentList[position].email
        holder.binding.tvTitle.text = currentList[position].first_name
        holder.binding.tvDesc.text = currentList[position].last_name

        holder.binding.linearLayoutMain.setOnClickListener {
            if (!NewsCounter.readedNews.contains(currentList[position])) {
                Log.i("Tag", "OnNewsRead")
                NewsCounter.onNewsRead()
                NewsCounter.readedNews.add(currentList[position])
            }
            val bundle = Bundle()
            bundle.putParcelable("ParcelableNews", currentList[position])
//            holder.itemView.findNavController()
//                .navigate(R.id.action_containerFragment_to_detailNewsFragment, bundle)
        }
    }
}
