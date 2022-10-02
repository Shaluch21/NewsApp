package com.quokkalabs.news.model


data class GetSearchRequest(
    val q: String,
    val from: String,
    val sortBy: String,
    val apiKey: String,
) {
    fun generateGetSearchRequestMap(): LinkedHashMap<String, String> {
        val getSearchRequestMap = LinkedHashMap<String, String>()
        getSearchRequestMap["q"] = q
        getSearchRequestMap["from"] = from
        getSearchRequestMap["sortBy"] = sortBy
        getSearchRequestMap["apiKey"] = apiKey
        return getSearchRequestMap
    }
}
