package com.quokkalabs.news.modules.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.quokkalabs.news.databinding.HomeFragmentBinding
import com.quokkalabs.news.model.GetNewsListResponse
import com.quokkalabs.news.modules.adapter.NewsListAdapter
import com.quokkalabs.news.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    var newslistAdapter: NewsListAdapter? = null
    private val viewModel by viewModels<HomeViewModel>()
    lateinit var manager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNewsList()
        getBreakingListResponseObserver()
    }

    private fun getBreakingListResponseObserver() {
        viewModel.breakingListResponse.observe(requireActivity()) {
            when (it.status) {
                Constants.SUCCESS -> processSearchResponse(it.data)
                Constants.ERROR -> showToast(it.message)
                Constants.LOADING -> _binding?.progressBar?.visibility = View.VISIBLE
            }
        }
    }

    private fun processSearchResponse(breakingListData: GetNewsListResponse?) {
        _binding?.progressBar?.visibility = View.GONE
        if (breakingListData?.articles?.size == 0) {
            _binding?.textViewEmpty?.visibility = View.VISIBLE
        } else {
            _binding?.textViewEmpty?.visibility = View.GONE
        }
        manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        _binding?.rvTopHeadlines?.layoutManager = manager
        newslistAdapter = NewsListAdapter(requireContext(), breakingListData?.articles)
        _binding?.rvTopHeadlines?.adapter = newslistAdapter

    }

    private fun showToast(message: String?) {
        message?.let {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }



}

