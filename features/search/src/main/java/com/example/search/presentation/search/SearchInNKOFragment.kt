package com.example.search.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.search.databinding.FragmentSearchInNKOBinding

class SearchInNKOFragment : Fragment() {
    private lateinit var binding: FragmentSearchInNKOBinding
    private val searchAdapter: SearchRecyclerViewAdapter by lazy {
        SearchRecyclerViewAdapter()
    }
    private lateinit var listOfSearchingString: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchInNKOBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcViewSearch.adapter = searchAdapter
        listOfSearchingString = mutableListOf()
        for (i in 0..4) {
            listOfSearchingString.add(java.util.UUID.randomUUID().toString())
        }

        searchAdapter.setupListAdapter(listOfSearchingString)
    }

    override fun onResume() {
        super.onResume()
        listOfSearchingString.shuffle()
        searchAdapter.setupListAdapter(listOfSearchingString)
    }
}
