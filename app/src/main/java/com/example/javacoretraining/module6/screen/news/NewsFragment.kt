package com.example.javacoretraining.module6.screen.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentNewsBinding
import com.example.javacoretraining.module6.screen.container.NewsCounter
import com.example.javacoretraining.module6.screen.container.NewsCounter.onFilterChanged
import com.example.javacoretraining.module6.screen.utils.ErrorDialog
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

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

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            ErrorDialog(throwable.cause?.message.toString(), throwable.message.toString()).show(
                requireActivity().supportFragmentManager,
                "ErrorDialog",
            )
        }

        viewLifecycleOwner.lifecycleScope.launch(handler) {
            NewsCounter.getUnreadCountFlow()
                .collect { count ->
                    if (tabLayout != null) {
                        tabLayout.getTabAt(0)?.orCreateBadge?.number = count
                    }
                }
        }

        viewModel.newsListJson.observe(viewLifecycleOwner) { list ->
            binding.progressBar.visibility = View.GONE
            Log.i("Tag", "List of news is updated ${list.size}")
            val newsCount = NewsCounter.getUnreadCountInt()
            if (newsCount == 0) {
                onFilterChanged(list.size)
            }
        }

        viewModel.newsListFromServer.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE
            if (response.code() == 200) {
                newsRecyclerViewAdapter.submitList(response.body()?.data)
            } else {
                // Делаем загрузку с json'а
            }
        }

        if (viewModel.newsListFromServer.value == null) {
            getFullListOfNews()
        }
    }

    private fun getFullListOfNews() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getListFromServer()
    }
}
