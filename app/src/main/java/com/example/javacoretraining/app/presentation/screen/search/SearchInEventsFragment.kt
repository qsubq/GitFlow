package com.example.javacoretraining.app.presentation.screen.search

import android.app.Application
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.datamodule.data.localDataSource.repository.LocalRepositoryImpl
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.javacoretraining.R
import com.example.javacoretraining.app.presentation.screen.news.NewsRecyclerViewAdapter
import com.example.core.utils.ErrorDialog
import com.example.javacoretraining.databinding.FragmentSearchInEventsBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@FlowPreview
class SearchInEventsFragment : Fragment() {
    private lateinit var binding: FragmentSearchInEventsBinding
    lateinit var viewModel: SearchViewModel
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }

//    @Inject lateinit var searchViewModelFactory: SearchViewModelFactory

    private val searchViewModelFactory: SearchViewModelFactory by lazy {
        SearchViewModelFactory(
            requireContext(),
            GetNewsFromDataBaseUseCase(
                LocalRepositoryImpl(
                    requireContext().applicationContext as Application,
                ),
            ),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (requireActivity().applicationContext as App).appComponent.inject(this@SearchInEventsFragment)

        viewModel = ViewModelProvider(this, searchViewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchInEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcViewSearch.adapter = newsRecyclerViewAdapter
        setSpannableText()
        showPlugView()

        viewModel.newsList.observe(viewLifecycleOwner) { list ->
            Log.i("Maksim", "News list загружен")
        }

        viewModel.searchString.observe(viewLifecycleOwner) { string ->
            val listOfNews = viewModel.newsList.value
            Log.i("Tag", "Observe searchString ${listOfNews?.size} items")

            val filteredList = listOfNews?.filter {
                Log.i("Tag", "Data:  ${it.first_name} items")
                it.first_name.contains(string)
            }

            if (string.isNotBlank()) {
                Log.i(
                    "Tag",
                    "Search is not empty and filtered list has ${filteredList?.size} items and string: $string",
                )
                showRecyclerView()
                newsRecyclerViewAdapter.submitList(filteredList)
            } else {
                Log.i("Tag", "Search is empty")
                showPlugView()
            }
        }

        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            com.example.core.utils.ErrorDialog(
                throwable.cause?.message.toString(),
                throwable.message.toString()
            ).show(
                requireActivity().supportFragmentManager,
                "ErrorDialog",
            )
        }
        viewLifecycleOwner.lifecycleScope.launch(handler) {
            val searchView: SearchView? = parentFragment?.view?.findViewById(R.id.newsSearchView)
            if (searchView != null) {
                searchView.getQueryTextChangeStateFlow()
                    .debounce(600)
                    .flowOn(Dispatchers.Unconfined)
                    .collect { result ->
                        viewModel.searchString.value = result
                    }
            }
        }

        viewModel.getListFromDataBase()
    }

    private fun showRecyclerView() {
        binding.tvExample.visibility = View.GONE
        binding.imgSearch.visibility = View.GONE
        binding.tvHelpText.visibility = View.GONE

        binding.rcViewSearch.visibility = View.VISIBLE
        binding.materialDivider.visibility = View.VISIBLE
        binding.materialDivider1.visibility = View.VISIBLE
    }

    private fun showPlugView() {
        binding.tvExample.visibility = View.VISIBLE
        binding.imgSearch.visibility = View.VISIBLE
        binding.tvHelpText.visibility = View.VISIBLE

        binding.rcViewSearch.visibility = View.GONE
        binding.materialDivider.visibility = View.GONE
        binding.materialDivider1.visibility = View.GONE
    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
        val searchQuery = MutableStateFlow("")
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchQuery.value = newText
                }
                return true
            }
        })
        return searchQuery
    }

    private fun setSpannableText() {
        val text = SpannableStringBuilder("Например, мастер-класс, помощь")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "Клик", Toast.LENGTH_SHORT).show()
            }
        }
        text.setSpan(clickableSpan, 10, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvExample.setText(text, TextView.BufferType.SPANNABLE)
        binding.tvExample.movementMethod = LinkMovementMethod.getInstance()
    }
}
