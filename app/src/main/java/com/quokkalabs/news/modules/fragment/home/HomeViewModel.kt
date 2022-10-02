package com.quokkalabs.news.modules.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quokkalabs.news.data.repository.MainRepository
import com.quokkalabs.news.model.GetNewsListResponse
import com.quokkalabs.news.model.GetTopHeadlines
import com.quokkalabs.news.utils.Constants
import com.quokkalabs.news.utils.NetworkHelper
import com.quokkalabs.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _breakingListResponse = MutableLiveData<Resource<GetNewsListResponse>>()

    val breakingListResponse: LiveData<Resource<GetNewsListResponse>>
        get() = _breakingListResponse


     fun getNewsList() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                        mainRepository.getTopHeadlines(
                            GetTopHeadlines(
                                "us",Constants.API_KEY
                            ).generateTopHeadlinesRequestMap()
                        ).let { response ->
                            if (response.isSuccessful) {
                                _breakingListResponse.postValue(Resource.success(data = response.body()))
                            } else
                                response.errorBody()?.string()?.let {
                                    _breakingListResponse.postValue(
                                        Resource.error(
                                            data = null,
                                            message = "No data found"
                                        )
                                    )
                                }
                        }
                    }
                else
                _breakingListResponse.postValue(
                    Resource.error(
                        data = null,
                        message = "You are not connected to the internet"
                    )
                )
        }
    }
}