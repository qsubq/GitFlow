package com.example.javacoretraining.module6.screen.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsListViewModel by activityViewModels<NewsListViewModel>()
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
                0,
                R.drawable.avatar_1,
                0,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.10)",
                "Благотворительный Фонд «Счастливый Мир»",
                "Санкт-Петербург, Кирочная улица,\n д. 50А, каб. 208",
                "+7 (937) 037 37-73\n+7 (937) 016 16-16",
            ),
            NewsItem(
                1,
                R.drawable.avatar_2,
                1,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.225)",
                "Благотворительный Фонд «Счастливый Мир»",
                "Санкт-Петербург, Кирочная улица,\n д. 50А, каб. 208",
                "+7 (937) 037 37-73\n+7 (937) 016 16-16",
            ),
            NewsItem(
                2,
                R.drawable.heart_circle_icon,
                0,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 13 дней (21.09 – 20.10)",
                "Благотворительный Фонд «Счастливый Мир»",
                "Санкт-Петербург, Кирочная улица,\n д. 50А, каб. 208",
                "+7 (937) 037 37-73\n+7 (937) 016 16-16",
            ),
            NewsItem(
                3,
                R.drawable.avatar_4,
                3,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 16 дней (21.09 – 20.10)",
                "Благотворительный Фонд «Счастливый Мир»",
                "Санкт-Петербург, Кирочная улица,\n д. 50А, каб. 208",
                "+7 (937) 037 37-73\n+7 (937) 016 16-16",
            ),
            NewsItem(
                4,
                R.drawable.avatar_3,
                3,
                "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей\n" +
                    "с ограниченными возможностями здоровья стала первой в области …",
                "Осталось 16 дней (21.09 – 20.10)",
                "Благотворительный Фонд «Счастливый Мир»",
                "Санкт-Петербург, Кирочная улица,\n д. 50А, каб. 208",
                "+7 (937) 037 37-73\n+7 (937) 016 16-16",
            ),
        )
        newsRecyclerViewAdapter.submitList(listOfNewsItems)

        newsListViewModel.filters.observe(viewLifecycleOwner) { set ->
            val newList = listOfNewsItems.filter {
                set.contains(it.category)
            }
            newsRecyclerViewAdapter.submitList(newList)
        }
    }
}
