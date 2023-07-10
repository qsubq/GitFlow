package com.example.javacoretraining.app.presentation.screen.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SearchInEventsFragment()
            }

            else -> {
                SearchInNKOFragment()
            }
        }
    }
}
