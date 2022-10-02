package com.quokkalabs.news.model

import com.squareup.moshi.Json

data class GenericResponse(
    @field:Json(name = "data")
    val data: String = "",
    @field:Json(name = "response")
    val response: String = "",
    @field:Json(name = "status")
    val status: String = "",

)
