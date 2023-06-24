package com.example.javacoretraining.module6.screen.search

import android.annotation.SuppressLint
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
import androidx.fragment.app.viewModels
import com.example.javacoretraining.R
import com.example.javacoretraining.databinding.FragmentSearchInEventsBinding
import com.example.javacoretraining.module6.screen.news.NewsItem
import com.example.javacoretraining.module6.screen.news.NewsRecyclerViewAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Random
import java.util.concurrent.TimeUnit

class SearchInEventsFragment : Fragment() {
    private lateinit var binding: FragmentSearchInEventsBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val newsRecyclerViewAdapter: NewsRecyclerViewAdapter by lazy {
        NewsRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchInEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcViewSearch.adapter = newsRecyclerViewAdapter
        setSpannableText()
        showPlugView()

        var listOfNews = emptyList<NewsItem>()
        viewModel.newsList.observe(viewLifecycleOwner) { list ->
            listOfNews = list
        }

        val listObservable = viewModel.getItemsFromJson()

        listObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.i("RxTraining", "doOnNext: ${Thread.currentThread().name}")
            }
            .subscribe { items ->
                Log.i("RxTraining", "onNext: ${Thread.currentThread().name}")
                viewModel.newsList.value = items
            }

        // Оператор объединения двух Observable
        val randomObservable = Observable.create<Int> { subscriber ->
            var x = 0
            while (!subscriber.isDisposed) {
                subscriber.onNext(Random().nextInt(100))
                Thread.sleep(1000)
            }
        }

        Observable.zip(listObservable, randomObservable) { items, random ->
            Pair(items, random)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .doOnNext { pair ->
                Log.i("RxExample", "Zip: ${Thread.currentThread().name}")
                Log.i("RxExample", "Zip: ${pair.first}, ${pair.second}")
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.i("RxExample", "doOnNext: ${Thread.currentThread().name}")
            }
            .subscribe { pair ->
                viewModel.newsList.value = pair.first
            }

        // Добавление двух observeOn в цепочке с разными планировщиками
        listObservable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                Log.i("RxExample", "doOnNext: ${Thread.currentThread().name}")
            }
            .subscribe { items ->
                Log.i("RxExample", "onNext: ${Thread.currentThread().name}")
                viewModel.newsList.value = items
            }

        viewModel.searchString.observe(viewLifecycleOwner) { string ->

            val filteredList = listOfNews.filter {
                it.title?.contains(string) ?: false
            }
            if (string.isNotEmpty()) {
                showRecyclerView()
                newsRecyclerViewAdapter.submitList(filteredList)
            } else {
                showPlugView()
            }
        }

        val searchView: SearchView? = parentFragment?.view?.findViewById(R.id.newsSearchView)
        if (searchView != null) {
            val observable = searchViewObservable(searchView)
            observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe() { query ->
                    viewModel.searchString.value = query
                }
        }
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

    @SuppressLint("CheckResult")
    private fun searchViewObservable(searchView: SearchView): Observable<String> {
        return Observable.create { emitter ->
            val textChangeObservable = Observable.create { observer ->
                val queryListener = object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        observer.onNext(newText ?: "")
                        return true
                    }
                }
                searchView.setOnQueryTextListener(queryListener)
                emitter.setCancellable {
                    searchView.setOnQueryTextListener(null)
                }
            }

            val debounceObservable = textChangeObservable.debounce(500, TimeUnit.MILLISECONDS)
            debounceObservable.subscribe { query ->
                emitter.onNext(query)
            }
        }
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
