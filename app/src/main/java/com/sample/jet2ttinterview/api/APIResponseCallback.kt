package com.sample.jet2ttinterview.api

import com.sample.jet2ttinterview.api.apiresponse.BlogListAPIResponse

interface APIResponseCallback {
    fun onErrorMessage(message: String?)
    fun onShowBlogs(blogsList: ArrayList<BlogListAPIResponse>?)
}