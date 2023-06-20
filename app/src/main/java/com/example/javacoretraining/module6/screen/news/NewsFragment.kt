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

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by activityViewModels<NewsListViewModel>()
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

        getList()

        var listOfNews = emptyList<NewsItem>()
        viewModel.newsList.observe(viewLifecycleOwner) { list ->
            binding.progressBar.visibility = View.GONE
            listOfNews = list
            newsRecyclerViewAdapter.submitList(list)
        }

        viewModel.filters.observe(viewLifecycleOwner) { set ->
            val newList = listOfNews.filter {
                set.contains(it.category)
            }
            newsRecyclerViewAdapter.submitList(newList)
        }
    }

    private fun getList() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getListFromJson()
    }
}
