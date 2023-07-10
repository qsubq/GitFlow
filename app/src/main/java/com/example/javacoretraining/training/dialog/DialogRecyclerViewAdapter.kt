package com.example.javacoretraining.training.dialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.javacoretraining.databinding.DialogItemLayoutBinding

class DialogRecyclerViewAdapter :
    RecyclerView.Adapter<DialogRecyclerViewAdapter.DialogViewHolder>() {
    class DialogViewHolder(val binding: DialogItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    private var listOfItems = emptyList<DialogItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val binding =
            DialogItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.binding.tvDialogName.text = listOfItems[position].text
        holder.binding.imgDialogIcon.background = listOfItems[position].drawableID
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DialogItem>) {
        listOfItems = list
        notifyDataSetChanged()
    }
}