package com.example.javacoretraining.app.presentation.screen.filter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.app.App
import com.example.javacoretraining.app.presentation.screen.news.NewsListViewModel
import com.example.javacoretraining.app.presentation.screen.news.NewsViewModelFactory
import com.example.javacoretraining.databinding.FragmentFilterBinding
import javax.inject.Inject

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private var viewModel: NewsListViewModel? = null

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as App).appComponent.inject(context as Activity)

        viewModel = ViewModelProvider(this, newsViewModelFactory)[NewsListViewModel::class.java]
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
            this.findNavController().navigate(R.id.action_filterFragment_to_containerFragment)
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

            viewModel?.filtersCategory?.value = numbersOfActivatedSwitches
            this.findNavController().navigate(R.id.action_filterFragment_to_containerFragment)
        }
    }
}
