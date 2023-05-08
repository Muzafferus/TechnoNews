package com.muzafferus.technonews.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
                    binding.tvContent.text = getString(R.string.no_article_found)
                } else {
                    showData(response)
                }
            }
        }
    }

    private fun showData(data: Article) {
        binding.tvTitle.text = data.title
        binding.tvDate.text = data.publishedAt
        binding.tvContent.text = data.content

        goneOrVisible(data.author, binding.tvAuthor)
        binding.tvAuthor.text = data.author

        goneOrVisible(data.url, binding.tvLink)
        binding.tvLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.url))
            startActivity(browserIntent)
        }

        binding.tvImg.load(data.urlToImage)
    }

    private fun goneOrVisible(text: String?, textView: TextView) {
        text?.let { textView.visibility = View.VISIBLE } ?: kotlin.run {
            textView.visibility = View.GONE
        }
    }
}