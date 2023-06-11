package com.example.javacoretraining.module6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.javacoretraining.module6.screen.HistoryFragment
import com.example.javacoretraining.module6.screen.NewsFragment
import com.example.javacoretraining.module6.screen.ProfileFragment
import com.example.javacoretraining.module6.screen.help.HelpFragment
import com.example.javacoretraining.module6.screen.search.SearchFragment

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
