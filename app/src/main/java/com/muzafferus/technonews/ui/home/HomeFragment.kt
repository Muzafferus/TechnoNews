package com.muzafferus.technonews.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muzafferus.technonews.R
import com.muzafferus.technonews.data.entities.Article
import com.muzafferus.technonews.databinding.FragmentHomeBinding
import com.muzafferus.technonews.ui.BaseFragment
import com.muzafferus.technonews.util.OnClickListener
import com.muzafferus.technonews.util.Utility
import com.muzafferus.technonews.util.ViewState
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter
    private var list: ArrayList<Article> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initData()
    }

    private fun initData() {
        viewModel.newsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    binding.articleFetchProgress.visibility = View.VISIBLE
                    binding.articleNotFound.visibility = View.GONE
                    binding.rvArticle.visibility = View.GONE
                }
                is ViewState.Success -> {
                    val value = response.value
                    if (!value.isNullOrEmpty()) {
                        list = value
                    }

                    if (list.isEmpty()) {
                        binding.articleFetchProgress.visibility = View.GONE
                        binding.articleNotFound.visibility = View.VISIBLE
                        binding.rvArticle.visibility = View.GONE
                    } else {
                        val format: DateFormat =
                            SimpleDateFormat(Utility.UTC_FORMAT, Locale.ENGLISH)
                        val sortedList = list.sortedByDescending { format.parse(it.publishedAt) }
                        adapter.setList(sortedList)
                        binding.rvArticle.visibility = View.VISIBLE
                        binding.articleFetchProgress.visibility = View.GONE
                        binding.articleNotFound.visibility = View.GONE
                    }
                }
                is ViewState.Error -> {
                    binding.articleFetchProgress.visibility = View.GONE
                    binding.articleNotFound.visibility = View.VISIBLE
                    binding.rvArticle.visibility = View.GONE
                    binding.articleNotFound.text = response.message
                }
            }
        }

        if (list.isEmpty()) {
            viewModel.getArticleList()
        }
    }

    private fun initAdapter() {
        adapter = ArticleAdapter()

        adapter.setOnClick(object : OnClickListener<Article> {
            override fun clicked(clickObject: Article) {
                navController.navigate(R.id.action_homeFragment_to_detailFragment,
                    Bundle().apply {
                        putString("id", clickObject.id.toString())
                    })
            }
        })

        binding.rvArticle.layoutManager = LinearLayoutManager(context)
        binding.rvArticle.adapter = adapter
    }
}