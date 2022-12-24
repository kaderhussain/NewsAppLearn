package com.kader.newsappkdr.utils

sealed class Resource<T>(val status: Status, val data: T?, val message: String?) {
    class Success<T>(data: T?) : Resource<T>(Status.SUCCESS, data, null)
    class Error<T>(msg: String) : Resource<T>(Status.ERROR, null, msg)
    class Loading<T>() : Resource<T>(Status.LOADING, null, null)
}