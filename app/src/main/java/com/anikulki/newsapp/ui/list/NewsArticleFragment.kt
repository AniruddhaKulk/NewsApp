package com.anikulki.newsapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anikulki.newsapp.R
import com.anikulki.newsapp.databinding.FragmentNewsArticlesBinding
import com.anikulki.newsapp.utils.common.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NewsArticleFragment: Fragment(R.layout.fragment_news_articles) {

    private var _binding: FragmentNewsArticlesBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by viewModels<NewsArticleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewsArticlesBinding.bind(view)

        viewModel.getTopNews()

        viewModel.articlesLiveData.observe(viewLifecycleOwner){state ->
            when(state){
                is State.Loading -> binding.swipeRefresh.isRefreshing = true

                is State.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                }

                is State.Error -> binding.swipeRefresh.isRefreshing = false
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getTopNews()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}