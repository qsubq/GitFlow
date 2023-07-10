package com.example.javacoretraining.app.presentation.screen.news

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
import com.example.javacoretraining.app.presentation.screen.container.NewsCounter
import com.example.javacoretraining.app.presentation.screen.container.NewsCounter.onFilterChanged
import com.example.javacoretraining.app.presentation.screen.utils.ErrorDialog
import com.example.javacoretraining.data.model.listModel.Data
import com.example.javacoretraining.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by activityViewModels<NewsListViewModel>()
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getListFromServer()
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

        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE

            newsRecyclerViewAdapter.submitList(response as List<Data>)
            val newsCount = NewsCounter.getUnreadCountInt()
            if (newsCount == 0) {
                onFilterChanged(response.size)
            }
        }

        if (viewModel.newsList.value == null) {
            Log.i("Maksim", "Получение данных с сервера")
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getListFromDataBase()
        }
    }
}
