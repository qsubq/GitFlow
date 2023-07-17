package com.example.news.news

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.adapter.NewsRecyclerViewAdapter
import com.example.core.utils.NewsCounter
import com.example.core.utils.NewsCounter.onFilterChanged
import com.example.core.utils.navigate
import com.example.core.viewModel.NewsListViewModel
import com.example.datamodule.data.localDataSource.repository.LocalRepositoryImpl
import com.example.datamodule.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.domain.domain.useCase.GetNewsFromServerUseCase
import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
import com.example.news.R
import com.example.news.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsListViewModel
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }
    private val newsViewModelFactory: NewsViewModelFactory by lazy {
        NewsViewModelFactory(
            requireContext(),
            GetNewsFromServerUseCase(RemoteRepositoryImpl()),
            GetNewsFromDataBaseUseCase(
                LocalRepositoryImpl(
                    requireContext().applicationContext as Application,
                ),
            ),
            InsertNewsIntoDataBaseUseCase(
                LocalRepositoryImpl(
                    requireContext().applicationContext as Application,
                ),
            ),
        )
    }

//    @Inject lateinit var newsViewModelFactory: NewsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (requireActivity().applicationContext as App).appComponent.inject(this@NewsFragment)

        viewModel = ViewModelProvider(this, newsViewModelFactory)
            .get(NewsListViewModel::class.java)
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
            navigate(R.id.action_containerFragment_to_filterFragment)
        }
        binding.rvNews.adapter = newsRecyclerViewAdapter

//        val tabLayout = this.parentFragment?.view?.findViewById<TabLayout>(com.example.javacoretraining.R.id.tabLayout)

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            com.example.core.utils.ErrorDialog(
                throwable.cause?.message.toString(),
                throwable.message.toString(),
            ).show(
                requireActivity().supportFragmentManager,
                "ErrorDialog",
            )
        }

        viewLifecycleOwner.lifecycleScope.launch(handler) {
//            NewsCounter.getUnreadCountFlow()
//                .collect { count ->
//                    if (tabLayout != null) {
//                        tabLayout.getTabAt(0)?.orCreateBadge?.number = count
//                    }
//                }
        }

        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            binding.progressBar.visibility = View.GONE

            newsRecyclerViewAdapter.submitList(response)
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
