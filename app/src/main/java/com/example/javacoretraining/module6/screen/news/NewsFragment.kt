package com.example.javacoretraining.module6.screen.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentNewsBinding
import com.example.javacoretraining.module6.screen.container.NewsCounter
import com.example.javacoretraining.module6.screen.container.NewsCounter.onFilterChanged
import com.google.android.material.tabs.TabLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

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

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgToolbar.setOnClickListener {
            this.findNavController().navigate(R.id.action_containerFragment_to_filterFragment)
        }
        binding.rvNews.adapter = newsRecyclerViewAdapter

        val tabLayout = this.parentFragment?.view?.findViewById<TabLayout>(R.id.tabLayout)
        NewsCounter.getUnreadCount().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe() { count ->
                if (tabLayout != null) {
                    tabLayout.getTabAt(0)?.orCreateBadge?.number = count
                }
            }

        viewModel.newsList.observe(viewLifecycleOwner) { list ->
            binding.progressBar.visibility = View.GONE
            Log.i("Tag", "List of news is updated ${list.size}")
            val newsCount = NewsCounter.getUnreadCountInt()
            if (newsCount == 0) {
                onFilterChanged(list.size)
            }

            newsRecyclerViewAdapter.submitList(list)
        }
        viewModel.filtersCategory.observe(viewLifecycleOwner) { set ->
            val list = viewModel.newsList.value
            val filteredList = list?.filter {
                set.contains(it.category)
            }
            viewModel.newsList.value = filteredList

            if (filteredList != null) {
                Log.i("Tag", "filtered list is not null and size ${filteredList.size}")
                onFilterChanged(filteredList.size)
            }
            newsRecyclerViewAdapter.submitList(filteredList)
        }

        if (viewModel.newsList.value.isNullOrEmpty()) {
            getFullListOfNews()
        }
    }

    private fun getFullListOfNews() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getListFromJson()
    }
}
