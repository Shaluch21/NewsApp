package com.quokkalabs.news.data.api

import com.quokkalabs.news.model.GetNewsListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(@QueryMap getTopHeadlinesRequestMap: Map<String, String>): Response<GetNewsListResponse>

    @GET("v2/everything")
    suspend fun getSearch(@QueryMap getSearchRequestMap: Map<String, String>): Response<GetNewsListResponse>


}