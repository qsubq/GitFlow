package com.example.javacoretraining.app.presentation.screen.container

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.help.help.HelpFragment
import com.example.javacoretraining.app.presentation.screen.history.HistoryFragment
import com.example.javacoretraining.app.presentation.screen.profile.ProfileFragment
import com.example.news.news.NewsFragment
import com.example.search.search.SearchFragment

class BottomNavigationAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment()
            1 -> SearchFragment()
            2 -> HelpFragment()
            3 -> HistoryFragment()
            else -> ProfileFragment()
        }
    }
}
