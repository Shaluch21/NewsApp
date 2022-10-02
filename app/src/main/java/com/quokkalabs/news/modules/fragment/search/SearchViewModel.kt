package com.quokkalabs.news.modules.fragment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quokkalabs.news.data.repository.MainRepository
import com.quokkalabs.news.model.GetNewsListResponse
import com.quokkalabs.news.model.GetSearchRequest
import com.quokkalabs.news.utils.Constants
import com.quokkalabs.news.utils.NetworkHelper
import com.quokkalabs.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _searchResponse = MutableLiveData<Resource<GetNewsListResponse>>()

    val searchResponse: LiveData<Resource<GetNewsListResponse>>
        get() = _searchResponse

    fun search(query: String) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                        mainRepository.getSearch(
                            GetSearchRequest(
                                query,"2022-09-30","popularity",Constants.API_KEY
                            ).generateGetSearchRequestMap()
                        ).let { response ->
                            if (response.isSuccessful) {
                                _searchResponse.postValue(Resource.success(data = response.body()))
                            } else
                                response.errorBody()?.string()?.let {
                                    _searchResponse.postValue(
                                        Resource.error(
                                            data = null,
                                            message = "No data found"
                                        )
                                    )
                                }
                        }
                    }
                else
                _searchResponse.postValue(
                    Resource.error(
                        data = null,
                        message = "You are not connected to the internet"
                    )
                )
        }
    }
}