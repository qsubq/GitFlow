package com.example.javacoretraining.module6.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgToolbar.setOnClickListener {
            this.findNavController().navigate(R.id.action_containerFragment_to_filterFragment)
        }

        binding.rvNews.adapter = newsRecyclerViewAdapter
        val listOfNewsItems = listOf<NewsItem>(
            NewsItem(
                R.drawable.avatar_1,
                0,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.10)",
            ),
            NewsItem(
                R.drawable.avatar_2,
                1,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.225)",
            ),
            NewsItem(
                R.drawable.heart_circle_icon,
                0,
                "Спонсоры отремонтир школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.10)",
            ),
        )
        newsRecyclerViewAdapter.setupAdaptersList(listOfNewsItems)
    }
}
