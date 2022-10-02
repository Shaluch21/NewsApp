package com.quokkalabs.news.data.repository

import com.quokkalabs.news.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getTopHeadlines(topHeadlinesRequestMap: Map<String, String>) =
        apiHelper.getTopHeadlines(topHeadlinesRequestMap)

    suspend fun getSearch(searchRequestMap: Map<String, String>) =
        apiHelper.getSearch(searchRequestMap)


}