package com.quokkalabs.news.utils

/*
Wrapper class to wrap network call results.
*/
data class Resource<out T>(val status: String, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> =
            Resource(status = Constants.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Constants.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Constants.LOADING, data = data, message = null)
    }

}