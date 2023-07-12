package com.example.news.presentation.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news.databinding.FragmentNewsBinding
import com.example.news.presentation.news.NewsCounterNews.onFilterChanged
import com.example.news.utils.ErrorDialog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModels<NewsListViewModel>()
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
        }
        binding.rvNews.adapter = newsRecyclerViewAdapter

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            ErrorDialog(throwable.cause?.message.toString(), throwable.message.toString()).show(
                requireActivity().supportFragmentManager,
                "ErrorDialog",
            )
        }

        viewLifecycleOwner.lifecycleScope.launch(handler) {
            NewsCounterNews.getUnreadCountFlow()
                .collect { count ->
//                    if (tabLayout != null) {
//                        tabLayout.getTabAt(0)?.orCreateBadge?.number = count
//                    }
                }
        }

        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE

            newsRecyclerViewAdapter.submitList(response as List<com.example.data.model.listModel.Data>)
            val newsCount = NewsCounterNews.getUnreadCountInt()
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
