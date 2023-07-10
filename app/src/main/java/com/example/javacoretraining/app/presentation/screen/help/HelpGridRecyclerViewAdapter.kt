package com.example.javacoretraining.app.presentation.screen.help

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.javacoretraining.databinding.HelpCategoryItemLayoutBinding

class HelpGridRecyclerViewAdapter :
    RecyclerView.Adapter<HelpGridRecyclerViewAdapter.HelpCategoryViewHolder>() {
    class HelpCategoryViewHolder(val binding: HelpCategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var listOfCategoryItems: List<HelpRecyclerViewItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpCategoryViewHolder {
        val binding = HelpCategoryItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return HelpCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfCategoryItems.size
    }

    override fun onBindViewHolder(holder: HelpCategoryViewHolder, position: Int) {
        holder.binding.imgCategory.setImageResource(listOfCategoryItems[position].image)
        holder.binding.tvTitle.text = listOfCategoryItems[position].title
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupList(list: List<HelpRecyclerViewItem>) {
        listOfCategoryItems = list
        notifyDataSetChanged()
    }
}
