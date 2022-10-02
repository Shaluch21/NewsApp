package com.quokkalabs.news.modules.fragment.search
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.quokkalabs.news.databinding.SearchFragmentBinding
import com.quokkalabs.news.model.GetNewsListResponse
import com.quokkalabs.news.modules.adapter.NewsListAdapter
import com.quokkalabs.news.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    var searchAdapter: NewsListAdapter? = null
    private var searchString: String? = null
    private val viewModel by viewModels<SearchViewModel>()
    lateinit var manager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        _binding?.rvSearch?.layoutManager = manager

        setSearchResponseObserver()
        searchQueryListener()

    }

    private fun setSearchResponseObserver() {
        viewModel.searchResponse.observe(requireActivity()) {
            when (it.status) {
                Constants.SUCCESS -> processSearchResponse(it.data)
                Constants.ERROR -> showToast(it.message)
                Constants.LOADING -> _binding?.progressBar?.visibility = View.VISIBLE
            }
        }
    }

    private fun searchQueryListener() {
        _binding?.searchView?.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                lifecycleScope.launch{
                    delay(2000)
                   if (s.toString().length > 0) {
                        _binding?.progressBar?.visibility = View.VISIBLE
                        _binding?.rvSearch?.scrollToPosition(0)
                        viewModel.search(s.toString())
                        _binding?.rvSearch?.visibility = View.VISIBLE
                    } else {
                        searchAdapter?.searchDataList?.clear()
                        searchAdapter?.notifyDataSetChanged()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        }
        )
    }


    private fun processSearchResponse(searchData: GetNewsListResponse?) {
        _binding?.progressBar?.visibility = View.GONE
        if (searchData?.articles?.size == 0) {
            _binding?.textViewEmpty?.visibility = View.VISIBLE
        } else {
            _binding?.textViewEmpty?.visibility = View.GONE
        }

        searchAdapter = NewsListAdapter(requireContext(), searchData?.articles)
        _binding?.rvSearch?.adapter = searchAdapter

    }

    private fun showToast(message: String?) {
        message?.let {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}

