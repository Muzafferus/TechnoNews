package com.muzafferus.technonews.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muzafferus.technonews.data.entities.Article
import com.muzafferus.technonews.databinding.ListItemArticleBinding
import com.muzafferus.technonews.util.OnClickListener

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val articleList: ArrayList<Article> = arrayListOf()
    private lateinit var onClick: OnClickListener<Article>

    fun setList(articles: List<Article>) {
        articleList.clear()
        articleList.addAll(articles)
        notifyDataSetChanged()
    }

    fun setOnClick(onClick: OnClickListener<Article>) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ListItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articleList[position])
        holder.getMain().setOnClickListener {
            onClick.clicked(articleList[position])
        }
    }

    override fun getItemCount(): Int = articleList.size

    class ArticleViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun getMain(): LinearLayout {
            return binding.main
        }

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvImg.load(article.urlToImage)
        }
    }
}