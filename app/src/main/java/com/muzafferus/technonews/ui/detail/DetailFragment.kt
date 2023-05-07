package com.muzafferus.technonews.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.muzafferus.technonews.R
import com.muzafferus.technonews.data.entities.Article
import com.muzafferus.technonews.databinding.FragmentDetailBinding
import com.muzafferus.technonews.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    private var articleId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        articleId = arguments?.getString("id")
        if (articleId != null) {
            viewModel.getArticle(articleId ?: "").observe(viewLifecycleOwner) { response ->
                if (response == null) {
                    binding.articleFetchProgress.visibility = View.GONE
                    binding.articleNotFound.visibility = View.VISIBLE
                    binding.articleNotFound.text = getString(R.string.no_article_found)
                } else {
                    binding.articleNotFound.visibility = View.GONE
                    showData(response)
                }
            }
        }
    }

    private fun showData(data: Article) {
        binding.tvName.text = data.title
        binding.tvId.text = data.id.toString()
        binding.tvSymbol.text = data.description
        binding.imgArticle.load(data.urlToImage)
    }
}