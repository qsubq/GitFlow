package com.example.javacoretraining.module6.screen.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentFilterBinding
import com.example.javacoretraining.module6.screen.news.NewsListViewModel

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private val newsListViewModel by activityViewModels<NewsListViewModel>()
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

            newsListViewModel.filters.value = numbersOfActivatedSwitches
            this.findNavController().navigate(R.id.action_filterFragment_to_containerFragment)
        }
    }
}
