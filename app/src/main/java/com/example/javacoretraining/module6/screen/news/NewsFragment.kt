package com.example.javacoretraining.module6.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentNewsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsListViewModel by activityViewModels<NewsListViewModel>()
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgToolbar.setOnClickListener {
            this.findNavController().navigate(R.id.action_containerFragment_to_filterFragment)
        }
        binding.rvNews.adapter = newsRecyclerViewAdapter

        val gson = Gson()
        val jsonFileString =
            JsonConverterIntoArray().getJsonFromAssets(requireContext(), "Events.json")
        val typeToken = object : TypeToken<List<NewsItem>>() {}.type
        val newsItemFormJson: List<NewsItem> =
            gson.fromJson(jsonFileString, typeToken)

        newsRecyclerViewAdapter.submitList(newsItemFormJson)

        newsListViewModel.filters.observe(viewLifecycleOwner) { set ->
            val newList = newsItemFormJson.filter {
                set.contains(it.category)
            }
            newsRecyclerViewAdapter.submitList(newList)
        }
    }
}
