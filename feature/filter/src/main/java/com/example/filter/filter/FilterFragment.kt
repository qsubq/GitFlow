package com.example.filter.filter

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.utils.navigate
import com.example.core.viewModel.NewsListViewModel
import com.example.datamodule.data.localDataSource.repository.LocalRepositoryImpl
import com.example.datamodule.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.domain.domain.useCase.GetNewsFromServerUseCase
import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
import com.example.filter.R
import com.example.filter.databinding.FragmentFilterBinding
import com.example.news.news.NewsViewModelFactory

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private lateinit var newsListViewModel: NewsListViewModel
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
//        (requireActivity().applicationContext as App).appComponent.inject(this@FilterFragment)

        newsListViewModel = ViewModelProvider(this, newsViewModelFactory)
            .get(NewsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
            navigate(R.id.action_filterFragment_to_containerFragment)
        }
        binding.imgConfirm.setOnClickListener {
            val numbersOfActivatedSwitches: MutableSet<Int> = mutableSetOf()

            if (binding.switchMoney.isChecked) {
                numbersOfActivatedSwitches.add(0)
            }
            if (binding.switchStuff.isChecked) {
                numbersOfActivatedSwitches.add(1)
            }
            if (binding.switchProfHelp.isChecked) {
                numbersOfActivatedSwitches.add(2)
            }
            if (binding.switchVolunteering.isChecked) {
                numbersOfActivatedSwitches.add(3)
            }

            newsListViewModel.filtersCategory.value = numbersOfActivatedSwitches
            navigate(R.id.action_filterFragment_to_containerFragment)
        }
    }
}
