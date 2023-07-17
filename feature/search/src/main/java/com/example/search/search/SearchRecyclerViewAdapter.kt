package com.example.search.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.search.databinding.SearchRecyclerViewLayoutBinding

class SearchRecyclerViewAdapter :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: SearchRecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfSearchItems: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchRecyclerViewLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfSearchItems.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.tvTitle.text = listOfSearchItems[position]

        if (listOfSearchItems.lastIndex == position) {
            holder.binding.bottomMaterialDivider.visibility = View.GONE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupListAdapter(list: List<String>) {
        listOfSearchItems = list
        notifyDataSetChanged()
    }
}
