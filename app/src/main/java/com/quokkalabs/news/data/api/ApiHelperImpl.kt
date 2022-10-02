package com.quokkalabs.news.data.api

import com.quokkalabs.news.model.GetNewsListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getTopHeadlines(topHeadlinesRequestMap: Map<String, String>): Response<GetNewsListResponse> =
        apiService.getTopHeadlines(topHeadlinesRequestMap)

    override suspend fun getSearch(searchRequestMap: Map<String, String>): Response<GetNewsListResponse> =
        apiService.getSearch(searchRequestMap)


}