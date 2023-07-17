package com.example.help.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.help.R
import com.example.help.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    private val categoryRecyclerViewAdapter: HelpGridRecyclerViewAdapter by lazy {
        HelpGridRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHelpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcView.adapter = categoryRecyclerViewAdapter
        val listOfRVItems = listOf<HelpRecyclerViewItem>(
            HelpRecyclerViewItem(R.drawable.category_children_icon, "Дети"),
            HelpRecyclerViewItem(R.drawable.category_adult_image, "Взрослые"),
            HelpRecyclerViewItem(R.drawable.category_elderly_image, "Пожилые"),
            HelpRecyclerViewItem(R.drawable.category_animal_image, "Животные"),
            HelpRecyclerViewItem(R.drawable.category_event_image, "Мероприятия"),
        )
        categoryRecyclerViewAdapter.setupList(listOfRVItems)
    }
}
