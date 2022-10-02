package com.quokkalabs.news.model

import com.squareup.moshi.Json

data class GetNewsListResponse(
    @field:Json(name = "status")
    val status: String? = null,
    @field:Json(name = "message")
    val message: String? = null,
    @field:Json(name = "totalResults")
    val totalResults: Int? = null,
    @field:Json(name = "articles")
    val articles: MutableList<Articles>? = null
)

data class Articles(
    @field:Json(name = "source")
    val source: Source? = null,
    @field:Json(name = "author")
    val author: String? = null,
    @field:Json(name = "title")
    val title: String? = null,
    @field:Json(name = "description")
    val description: String? = null,
    @field:Json(name = "url")
    val url: String? = null,
    @field:Json(name = "urlToImage")
    val urlToImage: String? = null,
    @field:Json(name = "publishedAt")
    val publishedAt: String? = null,
    @field:Json(name = "content")
    val content: String? = null
)

data class Source(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "name")
    val name: String? = null
)
