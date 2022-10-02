package com.quokkalabs.news.data.api

import com.quokkalabs.news.model.GetNewsListResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getTopHeadlines(topHeadlinesRequestMap: Map<String, String>): Response<GetNewsListResponse>

    suspend fun getSearch(searchRequestMap: Map<String, String>): Response<GetNewsListResponse>



}