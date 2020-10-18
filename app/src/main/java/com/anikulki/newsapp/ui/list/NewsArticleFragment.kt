package com.anikulki.newsapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anikulki.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsArticleFragment: Fragment(R.layout.fragment_news_articles) {

    private val viewModel by viewModels<NewsArticleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTopNews()
    }
}