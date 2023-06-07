package com.example.javacoretraining.module6.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentContainerBinding
import com.example.javacoretraining.module6.BottomNavigationAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ContainerFragment : Fragment() {
    private lateinit var binding:FragmentContainerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContainerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = BottomNavigationAdapter(this)
        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.currentItem = 4


        TabLayoutMediator(binding.tabLayout, binding.viewPager2){tab, pos->
            when(pos){
                0->{
                    tab.setIcon(R.drawable.news_icon)
                    tab.text = getString(R.string.news)
                }
                1->{
                    tab.setIcon(R.drawable.baseline_search_24)
                    tab.text = getString(R.string.search)
                }
                2->{
                    tab.setIcon(R.drawable.heart_circle_icon)
                    tab.text = getString(R.string.to_help)
                    tab.icon?.setTint(requireContext().getColor(R.color.red))
                }
                3->{
                    tab.setIcon(R.drawable.baseline_history_24)
                    tab.text = getString(R.string.history)
                }
                else->{
                    tab.setIcon(R.drawable.profile_icon)
                    tab.text = getString(R.string.profile)
                }
            }
        }.attach()
    }

}