package com.anikulki.newsapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import com.anikulki.newsapp.databinding.ItemNewsArticlesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class NewsAdapter: ListAdapter<ArticleEntity, NewsAdapter.ArticleHolder>(DIFF_UTIL){

    companion object {
        private val DIFF_UTIL =
            object: DiffUtil.ItemCallback<ArticleEntity>(){
                override fun areItemsTheSame(
                    oldItem: ArticleEntity,
                    newItem: ArticleEntity
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ArticleEntity,
                    newItem: ArticleEntity
                ) = oldItem == newItem

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding = ItemNewsArticlesBinding.inflate(LayoutInflater.from(
            parent.context), parent, false)

        return ArticleHolder(binding)
    }


    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = getItem(position)
        if(article != null)
            holder.bind(article)
    }


    inner class ArticleHolder(private val binding: ItemNewsArticlesBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(article: ArticleEntity){
            binding.apply {

                tvArticleTitle.text = article.title

                Glide.with(itemView.context)
                    .load(article.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivArticle)

            }
        }
    }

}