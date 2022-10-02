package com.quokkalabs.news.model

import com.squareup.moshi.Json

data class GetTopHeadlines(
    val country: String,
    val apiKey: String,
) {
    fun generateTopHeadlinesRequestMap(): LinkedHashMap<String, String> {
        val getTopHeadlinesRequestMap = LinkedHashMap<String, String>()
        getTopHeadlinesRequestMap["country"] = country
        getTopHeadlinesRequestMap["apiKey"] = apiKey
        return getTopHeadlinesRequestMap
    }
}

